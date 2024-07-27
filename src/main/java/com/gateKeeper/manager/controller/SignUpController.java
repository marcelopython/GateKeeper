package com.gateKeeper.manager.controller;

import com.gateKeeper.manager.dto.SignUpRequestDTO;
import com.gateKeeper.manager.dto.SignUpResponseDTO;
import com.gateKeeper.manager.service.SignUpService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "signup")
public class SignUpController {

    private final SignUpService companyService;

    public SignUpController(SignUpService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<SignUpResponseDTO> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {

        SignUpResponseDTO signUpResponseDTO = this.companyService.register(signUpRequestDTO);

        return ResponseEntity.ok().body(signUpResponseDTO);

    }

}
