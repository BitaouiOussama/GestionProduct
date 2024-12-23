package com.example.salesmanagement.feignRepos;

import com.example.salesmanagement.dto.ClientDto;
import com.example.salesmanagement.dto.ProduitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="client-service")
public interface ClientFeign {

    @GetMapping("/clients/{id}")
    ClientDto getClientById(@PathVariable Long id);

    @GetMapping("/clients")
    List<ClientDto> getAllClients();

    @PutMapping("/clients/{id}")
    ClientDto updateClient(@PathVariable Integer id, @RequestBody ClientDto clientDto);
}
