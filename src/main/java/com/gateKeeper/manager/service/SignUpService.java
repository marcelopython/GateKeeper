package com.gateKeeper.manager.service;

import com.gateKeeper.manager.dto.SignUpRequestDTO;
import com.gateKeeper.manager.dto.SignUpResponseDTO;
import com.gateKeeper.manager.model.Company;
import com.gateKeeper.manager.model.User;
import com.gateKeeper.manager.repository.CompanyRepository;
import com.gateKeeper.manager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final User user;

    private final Company company;

    public SignUpService(CompanyRepository companyRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, User user, Company company) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.user = user;
        this.company = company;
    }

    public SignUpResponseDTO register(SignUpRequestDTO signUpRequestDTO) {

        this.validateSignUpRequest(signUpRequestDTO.getEmail());

        this.company.setName(signUpRequestDTO.getName());
        this.company.setEmail(signUpRequestDTO.getEmail());
        this.company.setPhone(signUpRequestDTO.getPhone());
        this.company.setDocument(signUpRequestDTO.getDocument());

        Company newCompany = this.companyRepository.save(this.company);

        this.user.setName(signUpRequestDTO.getName());
        this.user.setEmail(signUpRequestDTO.getEmail());
        this.user.setPhone(signUpRequestDTO.getPhone());
        this.user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        this.user.setCompanyId(newCompany);

        this.userRepository.save(this.user);

        return new SignUpResponseDTO(this.company.getName(), this.company.getEmail());
    }

    private void validateSignUpRequest(String email){

        if(this.companyRepository.existsByEmail(email)){
            throw new IllegalArgumentException("A company with this email already exists");
        }

    }

}
