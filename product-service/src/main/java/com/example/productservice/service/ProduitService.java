package com.example.productservice.service;

import com.example.productservice.dtos.ProduitDto;

import java.util.List;

public interface ProduitService {

    public List<ProduitDto> getAllProducts();
    public ProduitDto getProduitById(Integer id);

    public ProduitDto finByName(String libelle);
    public ProduitDto findByMarque(String marque);
    public ProduitDto findByPriceAndMarque(float prix,String marque);
    public ProduitDto saveProduct(ProduitDto produitDto);

    public ProduitDto deleteById(Integer id);
    public ProduitDto updateProduct(Integer id, ProduitDto produitDto);


}
