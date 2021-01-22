package com.kjm.anonchat.auth.service;

import com.kjm.anonchat.auth.models.User;
import com.kjm.anonchat.auth.repository.UserRepository;
import com.kjm.anonchat.auth.web.MessageResponse;
import com.kjm.anonchat.auth.web.UserSummary;
import com.kjm.anonchat.chat.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        log.info("retrieving all users");
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        log.info("retrieving user {}", username);
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(String id) {
        log.info("retrieving user {}", id);
        return userRepository.findById(id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean update(UserSummary userSummary) {
        User user = findById(userSummary.getId())
                .orElseThrow(() -> new ResourceNotFoundException(userSummary.getUsername()));

        if(userSummary.getEmail().equals(user.getEmail())) {
            user.setProfilePicture(userSummary.getProfilePicture());
            userRepository.save(user);
            return true;
        }

        if (existsByEmail(userSummary.getEmail()) && !userSummary.getEmail().equals(user.getEmail())) {
            return false;
        }

        user.setEmail(userSummary.getEmail());
        user.setProfilePicture(userSummary.getProfilePicture());
        userRepository.save(user);
        return true;
    }
}
