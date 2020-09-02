package com.eduport.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String h1;
    String p1;
    String price;
    String p2;


    @ElementCollection
    List<String> list;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    PricingPage pricingPage;


}
