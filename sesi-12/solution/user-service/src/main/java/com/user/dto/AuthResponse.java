package com.user.dto;

public class AuthResponse {

    private String token;

    // Constructor kosong
    public AuthResponse() {}

    // Constructor lengkap
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter dan Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
