package com.eduport.demo.controller;


import com.eduport.demo.entity.*;
import com.eduport.demo.repo.ClientRepository;
import com.eduport.demo.security.JWTUtil;
import com.eduport.demo.service.IAdminService;
import com.eduport.demo.service.IContentSevice;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {

    @Autowired
    IContentSevice contentSevice;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    IAdminService adminService;

    @Autowired
    ClientRepository clientRepository;


    @PostMapping(value="/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Admin admin){
      //  log.info(admin.getAdminId() +"  "+admin.getAdminSecret());

        Authentication token= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getAdminId(),admin.getAdminSecret(),new ArrayList<>()));
        if(token.isAuthenticated()){
            String jwt=jwtUtil.generateToken(adminService.loadUserByUsername(admin.getAdminId()));
            String authorization="Bearer "+jwt;
            HttpHeaders headers=new HttpHeaders();
            headers.add("Authorization",authorization);
            return new ResponseEntity("Authenticated",headers, HttpStatus.OK);
        }
        return  new ResponseEntity<>("INVALID CREDENTIALS",HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping(value = "")
    public void updateAdmin(@RequestBody Admin admin){
        adminService.upDateAdmin(admin);
    }

    @PostMapping("/landingpage")
    public void updateLandingPage(@RequestBody LandingPage landingPage){
        contentSevice.updateLandingPage(landingPage);
    }

    @PostMapping("/pricingpage")
    public void updatePricingPage(@RequestBody PricingPage pricingPage){
        contentSevice.updatePricingPage(pricingPage);
    }

    @PostMapping("/blogspage")
    public void updateBlogsPage(@RequestBody Blog[] blogs){
        contentSevice.updateBlogsPage(blogs);
    }

    @PostMapping("/contactpage")
    public void updateContactPage(@RequestBody ContactPage contactPage){
        contentSevice.updateContactPage(contactPage);
    }

    @GetMapping("/client/{counselling}/{done}/{search}/{page}")
    public ResponseEntity<?> getClient(@PathVariable("counselling") boolean counselling,@PathVariable("done") boolean done,@PathVariable("page") int page,@PathVariable("search") String search){

        Sort sort;
        Pageable pageable= PageRequest.of(page, 3);

        HashMap<String,Object>hashMap= new HashMap<>();

        search=search.equals("all")?"":search;

        hashMap.put("totalPages",(int)Math.ceil((clientRepository.getFilterClientCount(counselling,done,search)/3.0)));
              hashMap.put("content",clientRepository.getFilteredClient(counselling,done,search, pageable));

       return new ResponseEntity<>(hashMap,HttpStatus.OK);
    }

    @PostMapping("/doneToggle")
    public  void doneToggler(@RequestParam("email")String email){

       Optional<Client>clientOptional= clientRepository.findByEmail(email);
        if(!clientOptional.isPresent())
            return;
        Client client=clientOptional.get();
        client.setDone(!client.isDone());
        clientRepository.save(client);
    }

}
