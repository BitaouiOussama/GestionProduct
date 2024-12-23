package com.example.authservice.dtos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class RegistrerResponse {
    private Long userId;
    private String username;
    //private String message;

}
