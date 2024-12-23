package com.example.productservice.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProduitDto {

    private Integer id;

    private String libelle;
    private String description;
    //une colonne marque sera crée dans la table produits
    private String marque;
    //la colonne prix
    private float prix;
    //la colonne qteStock
    private int qteStock;
    private String image;


}
