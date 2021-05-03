package com.binarybeasts.sport.controllers;

import com.binarybeasts.sport.exceptions.ClientInvalidException;
import com.binarybeasts.sport.models.*;
import com.binarybeasts.sport.services.ClientService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.lang.reflect.Type;
import org.modelmapper.TypeToken;

import java.util.UUID;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    ModelMapper modelMapper;

    // handle all crud operations for user

    @GetMapping("/allclients")
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<ClientResponse> findAll() {
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

        client = clientService.saveClient(client);

        ClientResponse clientResponse = modelMapper.map(client, ClientResponse.class);
        return clientResponse;
    }

    ClientResponse findOne(@PathVariable Long clientId) {
        // TODO
        return null;
    }

   ClientResponse saveOrUpdate(@RequestBody ClientRequest clientRequest, @PathVariable UUID clientId) {
        // TODO
        return null;
    }

    void deleteClient(@PathVariable UUID clientId) {
        // TODO
    }
}
