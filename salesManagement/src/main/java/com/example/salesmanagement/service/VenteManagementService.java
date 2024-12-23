package com.example.salesmanagement.service;

import com.example.salesmanagement.dto.ClientDto;
import com.example.salesmanagement.dto.VenteDto;

import java.util.List;

public interface VenteManagementService {


        VenteDto createVente(VenteDto venteDto, ClientDto clientDto);
        List<VenteDto> getAllVentes();
        VenteDto getVenteById(Long id, ClientDto clientDto);

}
