package com.example.salesmanagement.dto;

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
}
