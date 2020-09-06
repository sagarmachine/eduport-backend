package com.eduport.demo.service;

import com.eduport.demo.entity.*;
import org.springframework.http.ResponseEntity;

public interface IContentSevice {

    ResponseEntity <?>  getContent();

    void updateLandingPage(LandingPage landingPage);
    void updatePricingPage(PricingPage pricingPage);
    void updateBlogsPage(Blog[] blogs);
    void updateContactPage(ContactPage contactPage);
    void updateAboutPage(AboutPage aboutPage);
}
