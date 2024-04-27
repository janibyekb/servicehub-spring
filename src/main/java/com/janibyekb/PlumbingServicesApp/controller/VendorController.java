package com.janibyekb.PlumbingServicesApp.controller;


import com.janibyekb.PlumbingServicesApp.model.User;
import com.janibyekb.PlumbingServicesApp.model.Vendor;
import com.janibyekb.PlumbingServicesApp.services.UserService;
import com.janibyekb.PlumbingServicesApp.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin(origins ="http://localhost:3000")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @GetMapping("{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        Optional<Vendor> userByUsername = vendorService.findUserByUsername(username);

        return ResponseEntity.ok(userByUsername);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllVendors() {
        List<Vendor> users = vendorService.findAllVendors();
        return ResponseEntity.ok(users);
    }

}
