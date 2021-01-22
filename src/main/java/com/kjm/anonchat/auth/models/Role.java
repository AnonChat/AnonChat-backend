package com.kjm.anonchat.auth.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Role {
    @Id
    private String id;

    @NonNull
    private RoleEnum name;
}
