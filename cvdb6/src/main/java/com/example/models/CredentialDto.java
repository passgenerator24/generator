package com.example.models;

import jakarta.validation.constraints.NotEmpty;

public class CredentialDto {

    @NotEmpty(message="Please enter an account")
    private String account;

    @NotEmpty(message="Please enter account username")
    private String username;

    @NotEmpty(message="Please enter account password")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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