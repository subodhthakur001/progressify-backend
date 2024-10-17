package com.example.progressify.controller;


import com.example.progressify.dao.UserRepository;
import com.example.progressify.dto.request.auth.LoginRequest;
import com.example.progressify.dto.request.auth.SignUpRequest;
import com.example.progressify.dto.response.auth.LoginResponse;
import com.example.progressify.dto.response.auth.SignUpResponse;
import com.example.progressify.model.User;
import com.example.progressify.service.UserDetailsServiceImpl;
import com.example.progressify.service.UserService;
import com.example.progressify.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsServiceImpl userDetailsService,
                          UserService userService,
                          UserRepository userRepository,
                          JwtUtil jwtUtil){
        this.authenticationManager= authenticationManager;
        this.userDetailsService= userDetailsService;
        this.userService= userService;
        this.userRepository=userRepository;
        this.jwtUtil= jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
            userService.saveNewUser(signUpRequest);
            SignUpResponse response = new SignUpResponse("Signup successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            User dbUser = userRepository.findByUsername(loginRequest.getUsername());
            Long userId = dbUser.getId();
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername(),userId);
            LoginResponse response = new LoginResponse(jwt,"Login successful.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken ", e);
            throw  new RuntimeException("Incorrect password or username");
        }
    }

}
