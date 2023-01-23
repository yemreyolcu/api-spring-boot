package com.example.springbootapi.controllers;


import com.example.springbootapi.entities.User;
import com.example.springbootapi.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-list")
    public List<User> userListAPI() {
        return userService.usersList();
    }


    @PostMapping("/user-create")
    public User userCreateAPI(@RequestBody User newUser) {
        return userService.userCreate(newUser);
    }


    @GetMapping("/user/{userId}")
    public User userRetrieveAPI(@PathVariable Long userId) {
        return userService.userRetrieve(userId);
    }


    @PutMapping("/user/{userId}")
    public User userUpgradeAPI(@PathVariable Long userId, @RequestBody User requestUser) {
        return userService.userUpgrade(userId, requestUser);
    }


    @DeleteMapping("/user/{userId}")
    public void userDeleteAPI(@PathVariable Long userId) {
        userService.userDelete(userId);
    }
}
