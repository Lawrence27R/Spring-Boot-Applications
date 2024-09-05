package com.aurionpro.dboperations.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.entity.Client;
import com.aurionpro.dboperations.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Optional<Client> getClientById(int clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Page<Client> getAllClients(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clientRepository.findAll(pageable);
    }

    @Override
    public Page<Client> getClientByCompanyName(String companyName, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clientRepository.findByCompanyNameContaining(companyName, pageable);
    }
}
