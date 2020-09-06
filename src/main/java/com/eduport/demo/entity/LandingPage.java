package com.eduport.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LandingPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @Lob
    String r1h1;
    @Lob
    String r1p1;

    @Lob
    String r2h1;
    @Lob
    String r2p1;
    String r2h2;
    String r2h3;
    @ElementCollection
    List<R2List1> r2list1;
    @ElementCollection
    List<R2List2> r2list2;

    @Lob
    String r3h1;
    @Lob
    String r3h2;
    @Lob
    String r3h3;
    @Lob
    String r3h4;
    @Lob
    String r3h5;
    @Lob
    String r3h6;
    @Lob
    String r3h7;
    @Lob
    String r3p1;
    @Lob
    String r3p2;
    @Lob
    String r3p3;
    @Lob
    String r3p4;
    @Lob
    String r3p5;
    @Lob
    String r3p6;


    @ElementCollection
    List<R4> r4;

    @Lob
    String r4h1;


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



    @Lob
    String r8h1;
    @Lob
    String r8h2;

    @ElementCollection
    List<R9> r9;
    String r9h1;


    @ElementCollection
            List<Review>reviews;
    String reviewh1;

    @Lob
    String r10h1;

}


@Embeddable
class R9{
    @JsonProperty("h1")
    String h1;
    @JsonProperty("p1")
    String p1;
}

@Embeddable
class R2List1{

    @JsonProperty("icon")
    String icon;
    @JsonProperty("p1")
    String p1;
    @JsonProperty("p2")
    String p2;

}

@Embeddable
class R2List2{
@JsonProperty("icon")
String icon;

@JsonProperty("p1")
String p1;
}

@Embeddable
class R4{

    @JsonProperty("h1")
    String h1;
    @JsonProperty("p1")
    String p1;
}


@Embeddable
class Review{

    @JsonProperty("name")
    String name;
    @JsonProperty("comment")
    String comment;
    @JsonProperty("rating")
    String rating;

}