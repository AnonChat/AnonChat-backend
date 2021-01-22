package com.kjm.anonchat.auth.web;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class JwtResponse {
    @NonNull
    private String token;

    private String type = "Bearer";

    @NonNull
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private List<String> roles;
}
