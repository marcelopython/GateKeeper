package com.gateKeeper.manager.controller;

import com.gateKeeper.manager.dto.LoginRequestDTO;
import com.gateKeeper.manager.dto.RegisterRequestDTO;
import com.gateKeeper.manager.dto.ResponseAuthDTO;
import com.gateKeeper.manager.infra.security.TokenService;
import com.gateKeeper.manager.model.User;
import com.gateKeeper.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO body){

        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(body.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok().body(token);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseAuthDTO> register(@RequestBody RegisterRequestDTO body){
        Optional<User> checkIsUser = this.repository.findByEmail(body.email());

        if(checkIsUser.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        User user = new User();
        user.setPassword(passwordEncoder.encode(body.password()));
        user.setEmail(body.email());
        user.setName(body.name());
        this.repository.save(user);

        String token = this.tokenService.generateToken(user);
        return ResponseEntity.ok().body(new ResponseAuthDTO(user.getName(), token));

    }

}
