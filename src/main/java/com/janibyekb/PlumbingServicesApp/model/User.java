package com.janibyekb.PlumbingServicesApp.model;


import com.janibyekb.PlumbingServicesApp.enums.AuthorityEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String username;
    private String password;
    private String email;
    final String role= String.valueOf(AuthorityEnum.USER);
    private String profileImageUrl;
    private String address;
    private String phoneNumber;




    public User(String name, String hashedPassword, String phoneNumber, String email) {
        this.name=name;
        //TODO
//        this.username=name.split(" ")[0].toLowerCase()+(int)(Math.random()*100);
        this.username=email.split("@")[0];
        this.password=hashedPassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public User(String name, String hashedPassword, String email) {
        this.name=name;
        this.username=email.split("@")[0];
        this.password=hashedPassword;
        this.email=email;
    }
    //private List<Job> jobs = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
