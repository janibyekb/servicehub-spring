package com.janibyekb.PlumbingServicesApp.controller;


import com.janibyekb.PlumbingServicesApp.model.User;
import com.janibyekb.PlumbingServicesApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins ="http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        Optional<User> userByUsername = userService.findUserByUsername(username);

        return ResponseEntity.ok(userByUsername);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

}
