package com.example.productservice.web;


import com.example.productservice.dtos.ProduitDto;
import com.example.productservice.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // Autres méthodes

    @GetMapping
    public List<ProduitDto> getAllProducts() {
        return produitService.getAllProducts();
    }

    @GetMapping("/search")
    public ProduitDto findProduitByName(@RequestParam String libelle) {
        return produitService.finByName(libelle);
    }

    @DeleteMapping("/{id}")
    public ProduitDto deleteProduitById(@PathVariable Integer id) {
        return produitService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProduitDto> updateProduit(
            @PathVariable Integer id,
            @RequestBody ProduitDto produitDto) {
        ProduitDto updatedProduit = produitService.updateProduct(id, produitDto);
        return ResponseEntity.ok(updatedProduit);
    }

    @PostMapping("/ajouter")
    public ResponseEntity savProduitDto(@RequestBody ProduitDto produitDto){
        try {
            /*Sauvegarder le produit dans la table produits*/
            produitService.saveProduct(produitDto);
            /*status:201*/
            return new ResponseEntity<>(produitDto, HttpStatus.CREATED);
        } catch (Exception e) {
            /*Erreur 500*/
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    }

