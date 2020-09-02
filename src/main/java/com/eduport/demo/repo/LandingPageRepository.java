package com.eduport.demo.repo;

import com.eduport.demo.entity.LandingPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandingPageRepository extends JpaRepository<LandingPage,Integer> {
}
