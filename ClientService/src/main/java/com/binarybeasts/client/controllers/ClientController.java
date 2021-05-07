package com.binarybeasts.client.controllers;

import com.binarybeasts.client.exceptions.ClientInvalidException;
import com.binarybeasts.client.exceptions.ClientNotFoundException;
import com.binarybeasts.client.models.Client;
import com.binarybeasts.client.models.ClientRequest;
import com.binarybeasts.client.models.ClientResponse;
import com.binarybeasts.client.services.ClientService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.lang.reflect.Type;
import org.modelmapper.TypeToken;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    ModelMapper modelMapper;

    // handle all crud operations for user

    @GetMapping("/allclients")
    List<ClientResponse> findAll() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<Client> clients = clientService.getAllClients();

        Type responseType = new TypeToken<List<ClientResponse>>() {
        }.getType();

        List<ClientResponse> response = modelMapper.map(clients, responseType);
        return response;
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    ClientResponse newClient(@Valid @RequestBody ClientRequest clientRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Client client = modelMapper.map(clientRequest, Client.class);

        client = clientService.saveClient(client).orElseThrow(() -> new ClientInvalidException());;

        ClientResponse clientResponse = modelMapper.map(client, ClientResponse.class);
        return clientResponse;
    }

    @GetMapping("/clients/{id}")
    ClientResponse findOne(@PathVariable Long clientId) {
        Client client = clientService.getClientById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));

        ClientResponse clientResponse = modelMapper.map(client, ClientResponse.class);

        return clientResponse;
    }

    @PutMapping("/client/{id}")
    ClientResponse saveOrUpdate(@RequestBody ClientRequest clientRequest, @PathVariable Long clientId) {
        Client existentClient = clientService.getClientById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));

        Client newClient = modelMapper.map(clientRequest, Client.class);

        newClient.setId(existentClient.getId());

        Client updatedClient = clientService.saveClient(newClient).orElseThrow(() -> new ClientInvalidException());

        ClientResponse clientResponse = modelMapper.map(updatedClient, ClientResponse.class);

        return clientResponse;
    }

    @DeleteMapping("/clients/{id}")
    void deleteClient(@PathVariable Long clientId) {

        clientService.deleteClientById(clientId);
    }
}