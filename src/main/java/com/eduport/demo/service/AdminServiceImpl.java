package com.eduport.demo.service;

import com.eduport.demo.entity.Admin;
import com.eduport.demo.repo.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
public class AdminServiceImpl implements IAdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Autowired
    JavaMailSender emailSender;

    @Value("${eduport.email}")
    String adminEmail;

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

    @Override
    public void passwordResetReques(String url) {

       Admin admin= adminRepository.findAll().get(0);
        String key= UUID.randomUUID().toString();
         admin.setChangePasswordKey(key);
         adminRepository.save(admin);
       url=url+"/"+key;
        sendSimpleMessage(url);
    }

    @Override
    public void passwordReset(String key, String password, String newPassword)throws  Exception {
        Admin admin= adminRepository.findAll().get(0);

        log.info((admin.getChangePasswordKey()==null) +"" + (!key.equals(admin.getChangePasswordKey()) )+""+(!passwordEncoder.matches(password,admin.getAdminSecret())));

        if((admin.getChangePasswordKey()==null)  || (!key.equals(admin.getChangePasswordKey())) || (!passwordEncoder.matches(password,admin.getAdminSecret()))) {
            admin.setChangePasswordKey(null);
           adminRepository.save(admin);
            log.info("Exception");
             throw new RuntimeException();
        }

        admin.setChangePasswordKey(null);
        admin.setAdminSecret(passwordEncoder.encode(newPassword));
        adminRepository.save(admin);
    }

    public void sendSimpleMessage(String url) {
        //log.info("mail "+to);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            String inlineImage = "<h3>Eduport Password Reset - Verification</h3><br/><a href='"+url+"'>Click Here </a>";


            helper.setText(inlineImage, true);
            helper.setSubject("Eduport Admin Password Rest Request - Verification");
            //helper.setTo("sagarmonkey800@gmail.com");
            helper.setTo(adminEmail);
            helper.setFrom(adminEmail);
            emailSender.send(message);
            emailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
