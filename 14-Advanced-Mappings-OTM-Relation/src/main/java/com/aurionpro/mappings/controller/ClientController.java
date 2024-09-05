package com.aurionpro.mappings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.dto.ClientDto;
import com.aurionpro.mappings.dto.PageResponseDto;
import com.aurionpro.mappings.service.ClientService;

@RestController
@RequestMapping("/clientsApp")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/client")
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto) {
        return ResponseEntity.ok(clientService.addClient(clientDto));
    }

    @GetMapping("/clients")
    public ResponseEntity<PageResponseDto<ClientDto>> getAllClients(
            @RequestParam int pageNumber, 
            @RequestParam int pageSize) {
        return ResponseEntity.ok(clientService.getAllClients(pageNumber, pageSize));
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable int clientId) {
        ClientDto clientDto = clientService.getClientById(clientId);
        return ResponseEntity.ok(clientDto);
    }
}
