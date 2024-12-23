package com.example.clientservice.mapper;


import com.example.clientservice.dao.entities.Client;
import com.example.clientservice.dto.ClientDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    ModelMapper mapper = new ModelMapper();


    public ClientDto fromClientToClientDto(Client client) {
        return mapper.map(client, ClientDto.class);
    }

    public Client fromClientDtoToClient(ClientDto clientDto) {
        return mapper.map(clientDto, Client.class);
    }
}
