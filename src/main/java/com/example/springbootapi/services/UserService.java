package com.example.springbootapi.services;


import com.example.springbootapi.entities.User;
import com.example.springbootapi.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> usersList() {
        return userRepository.findAll();
    }

    public User userCreate(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public User userRetrieve(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User userUpgrade(Long userId, User requestUser) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            User updatedUser = foundUser.get();
            updatedUser.setUsername(requestUser.getUsername());
            updatedUser.setPassword(requestUser.getPassword());
            userRepository.save(updatedUser);
            return updatedUser;
        } else return null;
    }

    public void userDelete(Long userId) {
        userRepository.deleteById(userId );
    }


}
