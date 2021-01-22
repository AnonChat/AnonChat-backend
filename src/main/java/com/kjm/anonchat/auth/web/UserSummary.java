package com.kjm.anonchat.auth.web;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class UserSummary {

    private String id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @NonNull
    private String email;

    private String profilePicture;
}