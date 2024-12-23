package com.example.clientservice.service;

import com.example.clientservice.dto.ClientDto;

import java.util.List;

public interface ClientService {
    public List<ClientDto> getAllClients();

    public ClientDto getClientById(Long id);

    public ClientDto saveClient(ClientDto clientDto);

    public ClientDto deleteClient(Long id);
    public ClientDto updateClient(Long id, ClientDto clientDto);

}
