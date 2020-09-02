package com.eduport.demo.service;

import com.eduport.demo.entity.Blog;
import com.eduport.demo.entity.ContactPage;
import com.eduport.demo.entity.LandingPage;
import com.eduport.demo.entity.PricingPage;
import org.springframework.http.ResponseEntity;

public interface IContentSevice {

    ResponseEntity <?>  getContent();

    void updateLandingPage(LandingPage landingPage);
    void updatePricingPage(PricingPage pricingPage);
    void updateBlogsPage(Blog[] blogs);
    void updateContactPage(ContactPage contactPage);

}
