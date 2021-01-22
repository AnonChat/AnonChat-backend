package com.kjm.anonchat.auth.repository;

import com.kjm.anonchat.auth.models.RoleEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.kjm.anonchat.auth.models.Role;
import java.util.Optional;


public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(RoleEnum roleName);
}
