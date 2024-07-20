package com.gateKeeper.manager.infra.security;

import com.gateKeeper.manager.model.User;
import com.gateKeeper.manager.repository.UserRepository;
import com.gateKeeper.manager.service.Auth;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.gateKeeper.manager.service.Auth.*;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String login = tokenService.ValidateToken(
                this.recoverToken(request)
        );

        if(login != null){

            User user = userRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("User not found"));

            List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_USER")
            );

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            System.out.println(authentication);

            // Set user if you can authenticate
            if(authentication.isAuthenticated()){
                Auth.setUser(user);
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request){

        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return null;
        }
        System.out.println( authHeader.replace("Bearer ", ""));

        return authHeader.replace("Bearer ", "");

    }


}
