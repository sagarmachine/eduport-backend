package com.eduport.demo.controller;


import com.eduport.demo.entity.Client;
import com.eduport.demo.entity.ContactPage;
import com.eduport.demo.entity.LandingPage;
import com.eduport.demo.entity.PricingPage;
import com.eduport.demo.repo.*;
import com.eduport.demo.service.IContentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")

public class ClientController {

    @Autowired
    IContentSevice contentService;

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    LandingPageRepository landingPageRepository;

    @Autowired
    PricingPageRepository pricingPageRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ContactPageRepository contactPageRepository;

    @Autowired
    AboutPageRepository aboutPageRepository;


    @GetMapping("/content")
    public ResponseEntity<?> getContent(){

        return contentService.getContent();
    }


    int pageSize=12;


    @GetMapping("/content/landing")
    public ResponseEntity<?> landing(){
        LandingPage landingPage= landingPageRepository.findAll().get(0);

        return new ResponseEntity<>(landingPage, HttpStatus.OK);
    }
    @GetMapping("/content/pricing")
    public ResponseEntity<?> pricing(){
        PricingPage pricingPage=pricingPageRepository.findAll().get(0);
        return new ResponseEntity<>(pricingPage, HttpStatus.OK);
    }
    @GetMapping("/content/contact")
    public ResponseEntity<?> contact(){
        ContactPage contactPage=contactPageRepository.findAll().get(0);
        return new ResponseEntity<>(contactPage, HttpStatus.OK);
    }
    @GetMapping("/content/blogs/{page}")
    public ResponseEntity<?> blogs(@PathVariable("page")int page){
        Pageable pageable=PageRequest.of(page, pageSize, Sort.by("createdDate").descending());

        return new ResponseEntity<>(blogRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/content/about")
    public ResponseEntity<?> about(){

        return new ResponseEntity<>(  aboutPageRepository.findAll().get(0), HttpStatus.OK);

    }


    @PostMapping("/client")
    public void addClient(@RequestBody Client client){
             if(client.getEmail()!=null && client.getEmail().length()!=0) {

                 Optional<Client>optionalClient=  clientRepository.findByEmail(client.getEmail());
      if(optionalClient.isPresent())
            clientRepository.delete(optionalClient.get());
             }
             clientRepository.save(client);
    }


}
