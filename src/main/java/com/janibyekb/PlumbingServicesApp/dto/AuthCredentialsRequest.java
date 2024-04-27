package com.janibyekb.PlumbingServicesApp.dto;

public class AuthCredentialsRequest {

    //TODO manipulating email to username format
    private String email;
    private String password;

    public String getUsername() {
        return email.split("@")[0];
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
