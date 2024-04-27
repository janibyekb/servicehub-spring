package com.janibyekb.PlumbingServicesApp.services;

import com.janibyekb.PlumbingServicesApp.dto.SignUpRequest;
import com.janibyekb.PlumbingServicesApp.model.User;
import com.janibyekb.PlumbingServicesApp.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {


    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserRepo repository) {
        this.userRepo = repository;

    }

    public Optional<User> findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public void signup(SignUpRequest request) {
        String email = request.email();
       Optional<User> existingUser = userRepo.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new DuplicateKeyException(String.format("User with the email address '%s' already exists.", email));
        }

        String hashedPassword = passwordEncoder.encode(request.password());
        User user = new User(request.name(),  hashedPassword, request.email());
        userRepo.save(user);
    }

}
