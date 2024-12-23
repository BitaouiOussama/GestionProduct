package com.example.productservice.service;


import com.example.productservice.dao.entities.Produit;
import com.example.productservice.dao.repositories.ProduitRepository;
import com.example.productservice.dtos.ProduitDto;
import com.example.productservice.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProduitManager implements ProduitService {


    ProduitRepository produitRepository;

    ProductMapper productMapper;

    @Override
    public List<ProduitDto> getAllProducts() {
        // Récupérer tous les clients
        List<Produit> produits = produitRepository.findAll();

        // Créer une liste vide pour les DTO
        List<ProduitDto> produitDtos = new ArrayList<>();

        // Parcourir la liste des entités Client et les convertir en DTO
        for (Produit produit : produits) {
            produitDtos.add(productMapper.fromProduitToProduitDto(produit));
        }

        // Retourner la liste des DTO
        return produitDtos;
    }

    @Override
    public ProduitDto getProduitById(Integer id) {
        return productMapper.fromProduitToProduitDto(produitRepository.findById(id).get());
    }

    @Override
    public ProduitDto finByName(String libelle) {
        return productMapper.fromProduitToProduitDto(produitRepository.findByLibelle(libelle).get());
    }

    @Override
    public ProduitDto findByMarque(String marque) {
        return null;
    }

    @Override
    public ProduitDto findByPriceAndMarque(float prix, String marque) {
        return null;
    }

    @Override
    public ProduitDto saveProduct( ProduitDto produitDto) {
      produitRepository.save(productMapper.fromProduitDtoToProduit(produitDto));

        return productMapper.fromProduitToProduitDto(produitRepository.save(productMapper.fromProduitDtoToProduit(produitDto)));
    }

    @Override
    public ProduitDto deleteById(Integer id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit avec l'ID '" + id + "' non trouvé"));

        ProduitDto produitDTO = productMapper.fromProduitToProduitDto(produit);
        produitRepository.deleteById(id);
        return produitDTO;
    }

    @Override
    public ProduitDto updateProduct(Integer id, ProduitDto produitDto) {
        // Vérifier si le produit existe
        Produit existingProduit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit avec l'id " + id + " n'existe pas"));

        // Mettre à jour les champs avec les nouvelles valeurs
        existingProduit.setLibelle(produitDto.getLibelle());
        existingProduit.setMarque(produitDto.getMarque());
        existingProduit.setPrix(produitDto.getPrix());
        existingProduit.setQteStock(produitDto.getQteStock());
        existingProduit.setImage(produitDto.getImage());

        // Enregistrer le produit mis à jour
        Produit updatedProduit = produitRepository.save(existingProduit);

        // Convertir l'entité en DTO et la retourner
        return productMapper.fromProduitToProduitDto(updatedProduit);
    }


}
