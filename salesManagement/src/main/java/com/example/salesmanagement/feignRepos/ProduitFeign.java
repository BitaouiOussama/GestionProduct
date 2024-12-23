package com.example.salesmanagement.feignRepos;

import com.example.salesmanagement.dto.ProduitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProduitFeign {

    @GetMapping("/products/{id}")
    ProduitDto getProductById(@PathVariable Integer id);

    @PutMapping("/products/{id}")
    ProduitDto updateProduct(@PathVariable Integer id, @RequestBody ProduitDto produitDto);

    @GetMapping("/products")
    List<ProduitDto> getAllProducts();
}
