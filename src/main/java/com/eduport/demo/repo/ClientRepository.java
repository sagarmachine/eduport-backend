package com.eduport.demo.repo;

import com.eduport.demo.entity.Client;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByEmail(String email);


    @Query(value="select count(email) from Client where counselling=:counselling and done=:done and (name like %:search% OR email like %:search% )")
    Integer getFilterClientCount(boolean counselling,boolean done,String search);


    @Query(value="from Client where counselling=:counselling and done=:done and (name like %:search% OR email like %:search% )")
    List<Client>  getFilteredClient(boolean counselling,boolean done,String search, Pageable pageable);



}
