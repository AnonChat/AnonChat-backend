package com.kjm.anonchat.auth.web;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class JwtResponse {
    @NonNull
    private String token;

    private String type = "Bearer";

    @NonNull
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String profilePicture;

    @NonNull
    private String email;

    @NonNull
    private List<String> roles;

    public JwtResponse(String jwt, String id, String username, String profilePicture, String email, List<String> roles) {
        this.token = jwt;
        this.id = id;
        this.username = username;
        this.profilePicture = profilePicture;
        this.email = email;
    }
}
