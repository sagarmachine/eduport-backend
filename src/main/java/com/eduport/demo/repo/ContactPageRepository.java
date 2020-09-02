package com.eduport.demo.repo;

import com.eduport.demo.entity.ContactPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactPageRepository extends JpaRepository<ContactPage,Integer> {
}
