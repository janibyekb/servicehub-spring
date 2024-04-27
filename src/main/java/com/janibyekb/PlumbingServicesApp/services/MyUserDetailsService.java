package com.janibyekb.PlumbingServicesApp.services;


import com.janibyekb.PlumbingServicesApp.model.User;
import com.janibyekb.PlumbingServicesApp.model.UserPrincipal;
import com.janibyekb.PlumbingServicesApp.repo.UserRepo;
import com.janibyekb.PlumbingServicesApp.repo.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VendorRepo vendorRepo;
    @Override
    public UserDetails loadUserByUsername(String username)  {
        System.out.println(username);
        Optional<User> user= userRepo.findByUsername(username);

//        if(user==null)
//            user = vendorRepo.findByUsername(username);

        System.out.println(user);
        if (user.isEmpty()) {
            System.out.println("User 404");
            throw new UsernameNotFoundException("Invalid credentials");
        }
        return new UserPrincipal(user.get());
    }
    }

