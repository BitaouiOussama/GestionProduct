package com.example.clientservice.service;

import com.example.clientservice.dao.entities.Client;
import com.example.clientservice.dao.repositories.ClientRepository;
import com.example.clientservice.dto.ClientDto;
import com.example.clientservice.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientManager implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    ClientMapper clientMapper;

       @Override
    public List<ClientDto> getAllClients() {
        // Récupérer tous les clients
        List<Client> clients = clientRepository.findAll();

        // Créer une liste vide pour les DTO
        List<ClientDto> clientDtos = new ArrayList<>();

        // Parcourir la liste des entités Client et les convertir en DTO
        for (Client client : clients) {
            clientDtos.add(clientMapper.fromClientToClientDto(client));
        }

        // Retourner la liste des DTO
        return clientDtos;
    }

    @Override
    public ClientDto getClientById(Long id) {
        // Rechercher le client par ID et le convertir en DTO
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client avec l'id " + id + " non trouvé"));
        return clientMapper.fromClientToClientDto(client);
    }

    @Override
    public ClientDto saveClient(ClientDto clientDto) {
        // Convertir le DTO en entité, sauvegarder et convertir le résultat en DTO
        Client client = clientMapper.fromClientDtoToClient(clientDto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.fromClientToClientDto(savedClient);
    }

    @Override
    public ClientDto deleteClient(Long id) {
        // Rechercher le client, le convertir en DTO, puis le supprimer
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client avec l'id " + id + " non trouvé"));
        ClientDto clientDto = clientMapper.fromClientToClientDto(client);
        clientRepository.deleteById(id); // Suppression après conversion en DTO
        return clientDto;
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        // Rechercher le client existant par son ID
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client avec l'id " + id + " non trouvé"));

        // Mettre à jour les propriétés du client existant avec celles du DTO

            existingClient.setNom(clientDto.getNom());


            existingClient.setPrenom(clientDto.getPrenom());

            existingClient.setEmail(clientDto.getEmail());


            existingClient.setTel(clientDto.getTel());


        // Sauvegarder les modifications
        Client updatedClient = clientRepository.save(existingClient);

        // Convertir le client mis à jour en DTO et le retourner
        return clientMapper.fromClientToClientDto(updatedClient);
    }
}
