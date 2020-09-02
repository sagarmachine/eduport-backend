package com.eduport.demo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String h1;
    String h2;
    String h3;
    String c1;
    String c2;
    String c3;
    String term;


}
