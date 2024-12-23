package com.example.authservice.web;

import com.example.authservice.dtos.AuthRequest;
import com.example.authservice.dtos.AuthResponse;
import com.example.authservice.dtos.RegisterRequest;
import com.example.authservice.entities.UserEntity;
import com.example.authservice.repositories.UserRepository;
import com.example.authservice.service.CustomUserDetailsService;
import com.example.authservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController // Indicates that this class is a REST controller
@RequestMapping("/auth") // Maps all requests under /auth to this controller
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest user) {

        userDetailsService.registerUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "User registered successfully!"));
    }


    // Handles user login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // Authenticate the user using the AuthenticationManager
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // Generate a JWT token for the authenticated user
            String token = jwtUtil.generateToken(authRequest.getUsername());

            // Return the token in the response
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (AuthenticationException e) {
            // Handle authentication failures
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    // Handles user logout
    @PostMapping("/logout") // Maps POST requests to /auth/logout
    public ResponseEntity logout() {
        // In stateless JWT authentication, logout can be handled by deleting the token client-side
        return new ResponseEntity<>("User logged out successfully!", HttpStatus.OK); // Confirm logout
    }

    // A simple hello endpoint to confirm authentication
    @GetMapping("/hello") // Maps GET requests to /auth/hello
    public ResponseEntity hello() {
        // Return a response indicating successful authentication
        return new ResponseEntity<>("Hello! You are authenticated.", HttpStatus.OK);
    }
}
