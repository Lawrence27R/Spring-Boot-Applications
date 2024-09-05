package com.aurionpro.mappings.service;

import com.aurionpro.mappings.dto.ClientDto;
import com.aurionpro.mappings.dto.PageResponseDto;

public interface ClientService {
    ClientDto addClient(ClientDto clientDto);
    PageResponseDto<ClientDto> getAllClients(int pageNumber, int pageSize);
    ClientDto getClientById(int clientId);

}
