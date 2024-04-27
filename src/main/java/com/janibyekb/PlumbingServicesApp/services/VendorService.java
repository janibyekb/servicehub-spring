package com.janibyekb.PlumbingServicesApp.services;

import com.janibyekb.PlumbingServicesApp.dto.SignUpRequest;
import com.janibyekb.PlumbingServicesApp.model.User;
import com.janibyekb.PlumbingServicesApp.model.Vendor;
import com.janibyekb.PlumbingServicesApp.repo.UserRepo;
import com.janibyekb.PlumbingServicesApp.repo.VendorRepo;
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
public class VendorService {


    @Autowired
    VendorRepo vendorRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public VendorService(VendorRepo repository) {
        this.vendorRepo = repository;

    }
    public Optional<Vendor> findUserByUsername(String username) {
        return vendorRepo.findByUsername(username);
    }

    @Transactional
    public void signup(SignUpRequest request) {
        String username = request.email();
        Optional<User> existingUser = userRepo.findByEmail(username);
        if (existingUser.isPresent()) {
            throw new DuplicateKeyException(String.format("User with the email address '%s' already exists.", username));
        }

        String hashedPassword = passwordEncoder.encode(request.password());
        Vendor vendor = new Vendor(request.name(),  hashedPassword,  request.email());
        vendorRepo.save(vendor);
    }


    public List<Vendor> findAllVendors() {
        return vendorRepo.findAll();
    }

}
