package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.ClientDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.entity.Client;
import com.aurionpro.mappings.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepo;
    
    @Override
    public ClientDto addClient(ClientDto clientDto) {
        Client client = toClientMapper(clientDto);
        client = clientRepo.save(client);
        return toClientDtoMapper(client);
    }

    @Override
    public PageResponseDto<ClientDto> getAllClients(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Client> clientPage = clientRepo.findAll(pageable);

        List<ClientDto> clientDtoList = new ArrayList<>();
        for (Client client : clientPage.getContent()) {
            clientDtoList.add(toClientDtoMapper(client));
        }

        PageResponseDto<ClientDto> clientPageDto = new PageResponseDto<>();
        clientPageDto.setTotalPages(clientPage.getTotalPages());
        clientPageDto.setTotalElements(clientPage.getTotalElements());
        clientPageDto.setSize(clientPage.getSize());
        clientPageDto.setContents(clientDtoList);
        clientPageDto.setLastPage(clientPage.isLast());

        return clientPageDto;
    }

    @Override
    public ClientDto getClientById(int clientId) {
        Optional<Client> clientData = clientRepo.findById(clientId);
        if (clientData.isEmpty()) {
            return null;
        }
        Client dbClient = clientData.get();
        return toClientDtoMapper(dbClient);
    }

    private ClientDto toClientDtoMapper(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(client.getClientId());
        clientDto.setCompanyName(client.getCompanyName());
        clientDto.setRegistrationNumber(client.getRegistrationNumber());
        clientDto.setContactPerson(client.getContactPerson());
        clientDto.setContactEmail(client.getContactEmail());
        clientDto.setContactNumber(client.getContactNumber());
        clientDto.setAddress(client.getAddress());
        clientDto.setStatus(client.getStatus());
        clientDto.setCreationDate(client.getCreationDate());
        clientDto.setKycstatus(client.getKycstatus());
        return clientDto;
    }

    private Client toClientMapper(ClientDto clientDto) {
        Client client = new Client();
        client.setCompanyName(clientDto.getCompanyName());
        client.setRegistrationNumber(clientDto.getRegistrationNumber());
        client.setContactPerson(clientDto.getContactPerson());
        client.setContactEmail(clientDto.getContactEmail());
        client.setContactNumber(clientDto.getContactNumber());
        client.setAddress(clientDto.getAddress());
        client.setStatus(clientDto.getStatus());
        client.setCreationDate(clientDto.getCreationDate());
        client.setKycstatus(clientDto.getKycstatus());
        return client;
    }
}
