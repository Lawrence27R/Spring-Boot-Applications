package com.aurionpro.dboperations.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dboperations.entity.Client;
import com.aurionpro.dboperations.service.ClientService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/clientApp")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/clients")
    @Transactional
    public String addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return "Client added successfully";
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Optional<Client>> getClient(@PathVariable int clientId) {
        return ResponseEntity.ok(clientService.getClientById(clientId));
    }

    @PutMapping("/clients")
    @Transactional
    public String updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
        return "Client updated successfully";
    }

    @GetMapping("/clients")
    public ResponseEntity<Page<Client>> getClients(@RequestParam(required = false) String companyName,
                                                    @RequestParam int pageSize,
                                                    @RequestParam int pageNumber) {
        if (companyName != null) {
            return ResponseEntity.ok(clientService.getClientByCompanyName(companyName, pageSize, pageNumber));
        }
        return ResponseEntity.ok(clientService.getAllClients(pageSize, pageNumber));
    }
}
