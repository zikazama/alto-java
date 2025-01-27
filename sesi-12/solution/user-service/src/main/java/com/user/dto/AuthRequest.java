package com.user.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    // Constructor kosong
    public AuthRequest() {}

    // Constructor lengkap
    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter dan Setter
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
