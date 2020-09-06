package com.eduport.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PricingPage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToMany(mappedBy = "pricingPage", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Pricing> pricings;

    public void add(Pricing temp)
    {
        pricings.add(temp);
        temp.setPricingPage(this);
    }

    String r2h1;

   @ElementCollection
    List<Faq> faqs;

}


@Embeddable
@ToString
class Faq{

    @JsonProperty("ques")
    @Lob
    String ques;

    @JsonProperty("ans")
    @Lob
    String ans;


}
