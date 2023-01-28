package com.example.springbootapi.api.controllers;


import com.example.springbootapi.api.entities.User;
import com.example.springbootapi.api.exception.SQLExceptionH;
import com.example.springbootapi.api.services.UserService;
import com.example.springbootapi.config.security.jwt.http.AuthRequest;
import com.example.springbootapi.config.security.jwt.http.AuthResponse;
import com.example.springbootapi.config.security.jwt.service.JwtUtil;
import com.example.springbootapi.config.security.userdetail.CustomUserDetails;
import com.example.springbootapi.config.security.userdetail.UserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Value("${jwt.login.expire.hours}")
    private Long expireHours;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserDetailService userDetailsService, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex){
            throw new Exception("Incorrect username or password", ex);
        }
        final CustomUserDetails userDetails = userDetailService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails, expireHours);
        return new AuthResponse(userDetails.getUser().getId(), jwt, userDetails.getUsername());
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User addedUser;
        try{
            addedUser = this.userService.userCreate(user);
        }
        catch (DataIntegrityViolationException ex){
            throw new SQLExceptionH("There is already user this information. Check and try again.");
        }
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

}
