package com.example.productservice.dao.repositories;

import com.example.productservice.dao.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit,Integer> {
    Optional<Produit> findByLibelle(String libelle);
    //Optional<Produit> findByMarque(String marque);
    //Optional<Produit> findByPriceAndMarque(float prix,float marque);
}
