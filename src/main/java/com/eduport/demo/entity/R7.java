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


public class R7 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String comment;

    String name;

    double star;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    LandingPage landingPage;

}
