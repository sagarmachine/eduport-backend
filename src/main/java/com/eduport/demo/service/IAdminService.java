package com.eduport.demo.service;

import com.eduport.demo.entity.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAdminService  extends UserDetailsService {

    void upDateAdmin(Admin admin);


}
