package com.eduport.demo.controller;


import com.eduport.demo.entity.Client;
import com.eduport.demo.repo.ClientRepository;
import com.eduport.demo.service.IContentSevice;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/content")
    public ResponseEntity<?> getContent(){
        return contentService.getContent();
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
