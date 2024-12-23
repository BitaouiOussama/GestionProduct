package com.example.salesmanagement.mapper;

import com.example.salesmanagement.dto.VenteDto;
import com.example.salesmanagement.dao.entities.Vente;
import org.modelmapper.ModelMapper;

public class VenteMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public VenteDto fromVenteToVenteDto(Vente vente) {
        return modelMapper.map(vente, VenteDto.class);
    }

    public Vente fromVenteDtoToVente(VenteDto venteDto) {
        return modelMapper.map(venteDto, Vente.class);
    }
}
