package com.example.clientservice.dao.repositories;

import com.example.clientservice.dao.entities.Client;
import com.example.clientservice.dto.ClientDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {


}
