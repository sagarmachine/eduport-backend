package com.eduport.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LandingPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String r1h1;
    String r1p1;

    String r2h1;
    String r2p1;
    String r2h2;
    @ElementCollection
    List<String> r2list1;
    @ElementCollection
    List<String> r2list2;

    String r3h1;
    String r3h2;
    String r3h3;
    String r3h4;
    String r3h5;
    String r3h6;
    String r3h7;
    String r3p1;
    String r3p2;
    String r3p3;
    String r3p4;
    String r3p5;
    String r3p6;


    String r4h1;
    String r4h2;
    String r4h3;
    String r4h4;
    String r4p1;
    String r4p2;
    String r4p3;


    @OneToMany(mappedBy = "landingPage", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     List<R6> r5= new ArrayList<>();

    public void addR6(R6 temp)
    {
        r5.add(temp);
        temp.setLandingPage(this);
    }


    @OneToMany(mappedBy = "landingPage", cascade = CascadeType.ALL)
    List<R7> r7= new ArrayList<>();
    public void addR7(R7 temp)
    {
        r7.add(temp);
        temp.setLandingPage(this);
    }



    String r8h1;
    String r8h2;




}

