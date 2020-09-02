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


public class R6 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    String h1;
    String h2;

    String icon;

    @ElementCollection
    List<String> list;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    LandingPage landingPage;

}
