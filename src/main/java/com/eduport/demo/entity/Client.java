package com.eduport.demo.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    boolean done=false;

    String email;

    String phone;

    String name;

    boolean counselling=false;

    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date createdOn;

}
