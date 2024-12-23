package com.example.productservice.dao.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Produit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
//les constructeurs ,setter ,getter Sont obligatoires


}
