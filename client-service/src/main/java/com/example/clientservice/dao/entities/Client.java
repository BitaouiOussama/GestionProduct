package com.example.clientservice.dao.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Optional, but can be useful for clarity or customization
    private Long id;

    @Column(name = "nom", nullable = false, length = 100)  // Specifies non-null and a maximum length of 100 characters
    private String nom;

    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;

    @Column(name = "tel", length = 15)  // Limits the phone number length to 15 characters
    private String tel;

    @Column(name = "email", nullable = false, unique = true)  // Ensures email is unique and non-null
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name ="image", nullable = false )
    private String image;
}
