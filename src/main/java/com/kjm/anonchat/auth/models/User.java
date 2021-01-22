package com.kjm.anonchat.auth.models;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class User {

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.email = user.email;
        this.profilePicture = user.profilePicture;
        this.roles = user.roles;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = new HashSet<>() {{ new Role(RoleEnum.ROLE_USER); }};
    }

    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    @NonNull
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @NonNull
    private String email;

    @NotBlank
    @Size(max = 120)
    @NonNull
    private String password;

    private String profilePicture;

    @DBRef
    private Set<Role> roles;
}