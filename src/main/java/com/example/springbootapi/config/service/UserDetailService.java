package com.example.springbootapi.config.service;


import com.example.springbootapi.entities.User;
import com.example.springbootapi.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User foundUser = userRepository.findByUsername(username);

        if (foundUser == null)
            throw new UsernameNotFoundException("User not found!");

        return new CustomUserDetails(foundUser);
    }
}
