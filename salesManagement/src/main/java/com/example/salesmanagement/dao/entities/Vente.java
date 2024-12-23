package com.example.salesmanagement.dao.entities;

import com.example.salesmanagement.dto.ClientDto;
import feign.Client;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId; // ID du client
    private Integer productId; // ID du produit
    private int quantity;

    private LocalDateTime saleDate;
}
