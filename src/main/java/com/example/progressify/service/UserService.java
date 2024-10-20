package com.example.progressify.service;

import com.example.progressify.dto.request.auth.SignUpRequest;
import com.example.progressify.model.User;
import com.example.progressify.exception.DuplicateEntityException;
import com.example.progressify.dao.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(SignUpRequest signUpRequest) {
        if (userRepository.findByUsername(signUpRequest.getUsername()) != null) {
            throw new DuplicateEntityException("Username already exists: " + signUpRequest.getUsername());
        }
        try {
            User user = new User();
            user.setUsername(signUpRequest.getUsername());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setRole(signUpRequest.getRole());
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error while saving the user: " + e.getMessage());
            throw new RuntimeException("Error while saving the user: " + e.getMessage());
        }
    }
}

