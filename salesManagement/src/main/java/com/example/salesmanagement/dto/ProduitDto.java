package com.example.salesmanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProduitDto {

    private Integer id;

    private String libelle;
    //une colonne marque sera crée dans la table produits
    private String marque;
    //la colonne prix
    private float prix;
    //la colonne qteStock
    private int qteStock;


}
