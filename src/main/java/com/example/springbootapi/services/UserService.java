package com.example.springbootapi.services;


import com.example.springbootapi.entities.User;
import com.example.springbootapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> usersList() {
        return userRepository.findAll();
    }

    public User userCreate(User newUser) {
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
