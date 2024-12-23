package com.example.authservice.config;


// Import necessary Spring Security and Servlet classes for configuration.
import com.example.authservice.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import java.util.Arrays;
import java.util.List;

// The @Configuration annotation indicates that this class is a source of bean definitions.
// The @EnableWebSecurity annotation enables Spring Security’s web security support.
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // Automatically inject the custom UserDetailsService for loading user-specific data.
    @Autowired
    private CustomUserDetailsService userDetailsService;

    // Automatically inject the JwtRequestFilter to handle JWT validation for incoming requests.
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
   private PasswordConfig passwordConfig;

    // This bean provides a password encoder that will be used to encode passwords.

    // This bean defines the security filter chain, which contains the security configurations.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors->cors.disable())
                //.cors(cors -> cors.(corsConfigurationSource()))
                // Disabling CSRF for stateless APIs (JWT authentication)
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/auth/register","/auth/login","/h2-console/**").permitAll() // Allow public access to login/register
                                .anyRequest().authenticated() // Require authentication for all other requests
                ).sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Exception handling configuration for unauthorized access
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint((request, response, authException) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                        })
                )
                // Configure stateless session management (since we're using JWT)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Adding the custom JWT filter to the chain
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();

        return http.build();
    }

    /*
    @Bean
    public CorsWebFilter corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200/");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsWebFilter(source);
    }
    **/

    // This bean provides an AuthenticationManager for managing authentication.
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        // Get the shared AuthenticationManagerBuilder from the HttpSecurity context.
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        // Set the custom UserDetailsService and the password encoder for the authentication manager.
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)// Use the custom UserDetailsService for user data retrieval.
                .passwordEncoder(passwordConfig.passwordEncoder()); // Use the BCrypt password encoder for password matching.

        // Build and return the AuthenticationManager.
        return authenticationManagerBuilder.build();
    }
}
