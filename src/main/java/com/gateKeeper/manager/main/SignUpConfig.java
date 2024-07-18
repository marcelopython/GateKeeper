package com.gateKeeper.manager.main;

import com.gateKeeper.manager.controller.SignUpController;
import com.gateKeeper.manager.model.Company;
import com.gateKeeper.manager.model.User;
import com.gateKeeper.manager.repository.CompanyRepository;
import com.gateKeeper.manager.repository.UserRepository;
import com.gateKeeper.manager.service.SignUpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SignUpConfig {

    @Bean
    SignUpController signUpController(SignUpService SignUpService){
        return new SignUpController(SignUpService);
    }

    @Bean
    SignUpService signUpService(CompanyRepository companyRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, User user, Company company){
        return new SignUpService(companyRepository, userRepository, passwordEncoder, user, company);
    }

    @Bean
    User user(){
        return new User();
    }

    @Bean
    Company company(){
        return new Company();
    }

}
