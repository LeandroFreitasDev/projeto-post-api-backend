package br.com.sdvweb.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sdvweb.backend.DTO.LoginRequestDTO;
import br.com.sdvweb.backend.DTO.LoginResponseDTO;
import br.com.sdvweb.backend.security.CustomUserDetailsService;
import br.com.sdvweb.backend.security.JwtUtil;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO login) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha())
        );

        UserDetails user = userDetailsService.loadUserByUsername(login.getEmail());
        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
