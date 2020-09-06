package com.eduport.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Lob
    String r1h1;
    @Lob
    String r1p1;
    @Lob
    String r1p2;
    @Lob
    String r2h1;
    @Lob
    String r2p1;
    @Lob
    String r2p2;
    @Lob
    String r3h1;
    @Lob
    String r3p1;
    @Lob
    String r3p2;
    @Lob
    String r4h1;
    @Lob
    String r4p1;
    @Lob
    String r4p2;
    @Lob
    String r5h1;

    @ElementCollection
    List<Team> team;


}


@Embeddable
class Team{

    @JsonProperty("name")
    String name;
    @JsonProperty("position")
    String position;
    @JsonProperty("gender")
    String gender;
}
