package com.aurionpro.dboperations.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.aurionpro.dboperations.entity.Client;

public interface ClientService {

    void addClient(Client client);

    Optional<Client> getClientById(int clientId);

    void updateClient(Client client);

    Page<Client> getAllClients(int pageSize, int pageNumber);

    Page<Client> getClientByCompanyName(String companyName, int pageSize, int pageNumber);
}
