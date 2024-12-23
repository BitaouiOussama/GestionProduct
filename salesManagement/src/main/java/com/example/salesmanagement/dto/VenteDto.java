package com.example.salesmanagement.dto;

import java.time.LocalDateTime;

public class VenteDto {

    private Long id;
    private Long clientId;
    private Integer productId;
    private int quantity;
    private LocalDateTime saleDate;
    private ClientDto client;
    private ProduitDto product;
}
