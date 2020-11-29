package com.kjm.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.kjm.demo.models.Role;
import com.kjm.demo.models.RoleEnum;

import java.util.Optional;


public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByRoleName(RoleEnum roleName); //bez nulli
}
