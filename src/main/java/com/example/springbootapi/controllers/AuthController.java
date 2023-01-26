package com.example.springbootapi.controllers;


import com.example.springbootapi.config.service.JwtUtil;
import com.example.springbootapi.config.service.UserDetailService;
import com.example.springbootapi.entities.User;
import com.example.springbootapi.exception.SQLExceptionH;
import com.example.springbootapi.requests.AuthRequest;
import com.example.springbootapi.services.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;

    private final UserService userService;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserDetailService userDetailsService, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String createToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex){
            throw new Exception("Incorrect username or password", ex);
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return  "Bearer " + jwt;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User addedUser = null;
        try{
            addedUser = this.userService.userCreate(user);
        }
        catch (DataIntegrityViolationException ex){
            throw new SQLExceptionH("Sistemde bu bilgilere ait kayıt bulunmaktadır. Lütfen bilgilerinizi kontrol edin.");
        }
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

}
