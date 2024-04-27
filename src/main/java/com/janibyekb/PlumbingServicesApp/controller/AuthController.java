package com.janibyekb.PlumbingServicesApp.controller;


import com.janibyekb.PlumbingServicesApp.dto.AuthCredentialsRequest;
import com.janibyekb.PlumbingServicesApp.dto.SignUpRequest;
import com.janibyekb.PlumbingServicesApp.enums.AuthorityEnum;
import com.janibyekb.PlumbingServicesApp.model.User;
import com.janibyekb.PlumbingServicesApp.model.UserPrincipal;
import com.janibyekb.PlumbingServicesApp.services.UserService;
import com.janibyekb.PlumbingServicesApp.services.VendorService;
import com.janibyekb.PlumbingServicesApp.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Request;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins ="http://localhost:3000")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    @Autowired
    VendorService vendorService;

    @PostMapping("/register")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignUpRequest requestDto) {

        if(Objects.equals(requestDto.role(), "VENDOR")){
            System.out.println("creating a new vendor");
            vendorService.signup(requestDto);
        }else{
            System.out.println("creating a new user");
            userService.signup(requestDto);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/signout")
    public ResponseEntity<?> signout () {
        ResponseCookie cookie = ResponseCookie.from("access_token", "")
                .maxAge(0)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString()).body("ok");
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken (@CookieValue(value="access_token") String token, @AuthenticationPrincipal UserPrincipal user){
        Claims obj = jwtTokenUtil.getAllClaimsFromToken(token);
        System.out.println(obj);
        Boolean isTokenValid = jwtTokenUtil.validateToken(token, user);
        System.out.println(isTokenValid + " " + token);
        return ResponseEntity.ok(isTokenValid);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest req){

        System.out.println(req.getUsername()+" " + req.getPassword());


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(), req.getPassword()
                            )
            );
        System.out.println(authentication);
            UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateToken(user);
            if(authentication.isAuthenticated()){
                System.out.println("Access Token: "+ accessToken);
//                RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
//                String accessToken = jwtService.GenerateToken(authRequestDTO.getUsername());
                // set accessToken to cookie header
                //Optional<User> userInfo = userService.findUserByUsername(user.getUsername());
                User userInfo = user.getUser();
                System.out.println("UserInfo"+userInfo);
                ResponseCookie cookie = ResponseCookie.from("access_token", accessToken)
                        .httpOnly(true)
                        .secure(false)
                        .path("/")
                        //.maxAge(cookieExpiry)
                        .build();
                return ResponseEntity.ok().
                    header(HttpHeaders.SET_COOKIE, cookie.toString()).body(userInfo);
            } else {
                throw new UsernameNotFoundException("invalid user request..!!");
            }



    }


}
