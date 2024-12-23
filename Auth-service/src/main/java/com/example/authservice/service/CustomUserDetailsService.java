package com.example.authservice.service;

import com.example.authservice.dtos.RegisterRequest;
import com.example.authservice.dtos.RegistrerResponse;
import com.example.authservice.entities.UserEntity;
import com.example.authservice.repositories.UserRepository;
import com.example.authservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Return a UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(),
                user.get().getPassword(),
                new ArrayList<>() // Roles can be added here if needed
        );
    }

    public RegistrerResponse registerUser(RegisterRequest registerRequest) {
        // Vérification si l'utilisateur existe déjà
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
          throw new IllegalArgumentException("L'utilisateur avec ce nom existe déjà !");
        }

        // Création d'un nouvel utilisateur
        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setEmail(registerRequest.getEmail());
        userRepository.save(newUser);

         //Génération du JWT pour l'utilisateur enregistré
        //String token = jwtUtil.generateToken(newUser.getUsername());

         //Retour de la réponse avec les détails de l'utilisateur et le token
        return new RegistrerResponse(newUser.getId(),newUser.getUsername());
    }

}

