package com.example.clientservice.dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientDto {
    private Long id;

    private String nom;

    private String prenom;

    private String tel;

    private String email;

    private String password;

    private String image;
}
