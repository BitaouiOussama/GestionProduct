package com.example.salesmanagement.dao.repositories;

import com.example.salesmanagement.dao.entities.Vente;
import com.example.salesmanagement.dto.VenteDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenteRepository extends JpaRepository<Vente,Long> {

    List<VenteDto> findByClientId(Long clientId);
}
