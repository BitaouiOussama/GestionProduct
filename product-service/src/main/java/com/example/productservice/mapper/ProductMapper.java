package com.example.productservice.mapper;

import com.example.productservice.dao.entities.Produit;
import com.example.productservice.dtos.ProduitDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    ModelMapper mapper = new ModelMapper();


    public ProduitDto fromProduitToProduitDto(Produit produit) {
        return mapper.map(produit, ProduitDto.class);
    }

    public Produit fromProduitDtoToProduit(ProduitDto produitDto) {
        return mapper.map(produitDto, Produit.class);
    }
}
