package com.eduport.demo.service;

import com.eduport.demo.entity.Admin;
import com.eduport.demo.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Admin>optionalAdmin= adminRepository.findByAdminId(s);

        if(optionalAdmin.isPresent())
            return  new User(optionalAdmin.get().getAdminId(),optionalAdmin.get().getAdminSecret(), new ArrayList<>());

        return null;
    }

    @Override
    public void upDateAdmin(Admin admin) {
        adminRepository.deleteAll();
        admin.setAdminSecret(passwordEncoder.encode(admin.getAdminSecret()));
        adminRepository.save(admin);
    }
}
