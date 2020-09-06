package com.eduport.demo.service;

import com.eduport.demo.entity.*;
import com.eduport.demo.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@Slf4j
public class ContentSeviceImpl implements IContentSevice {

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

    @Override
    public ResponseEntity<?> getContent() {

        LandingPage landingPage= landingPageRepository.findAll().get(0);
        PricingPage pricingPage=pricingPageRepository.findAll().get(0);
       ContactPage contactPage=contactPageRepository.findAll().get(0);
        List<Blog> blogs=blogRepository.findAll();

        HashMap<String,Object> content=new HashMap<>();
        content.put("landingPage",landingPage);
        content.put("pricingPage",pricingPage);
        content.put("contactPage",contactPage);
        content.put("blogsPage",blogs);

        return new ResponseEntity<>(content, HttpStatus.OK);

    }

    @Override
    public void updateLandingPage(LandingPage landingPage) {

        landingPageRepository.deleteAll();

        log.info(landingPage.toString());

        List<R6> r5=landingPage.getR5();
        List<R7> r7=landingPage.getR7();

        landingPage.setR5(new ArrayList<>());
        landingPage.setR7(new ArrayList<>());

        for (R6 temp:r5){
              landingPage.addR6(temp);
        }

        for (R7 temp:r7){
            landingPage.addR7(temp);
        }
        landingPageRepository.save(landingPage);

    }

    @Override
    public void updatePricingPage(PricingPage pricingPage) {
    pricingPageRepository.deleteAll();

    List<Pricing> pricing = pricingPage.getPricings();
    pricingPage.setPricings(new ArrayList<>());
        for (Pricing temp:pricing){
            pricingPage.add(temp);
        }

        System.out.println(pricingPage.getFaqs());
    pricingPageRepository.save(pricingPage);
    }

    @Override
    public void updateBlogsPage(Blog[] blogs) {

        blogRepository.deleteAll();

        for (Blog blog :blogs)
             blogRepository.save(blog);
    }

    @Override
    public void updateContactPage(ContactPage contactPage) {
        contactPageRepository.deleteAll();
        contactPageRepository.save(contactPage);
    }

    @Override
    public void updateAboutPage(AboutPage aboutPage) {
        aboutPageRepository.deleteAll();
        aboutPageRepository.save(aboutPage);
    }
}
