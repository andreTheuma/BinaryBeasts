package com.binarybeasts.sport.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.binarybeasts.sport.dao.ClientDAO;
import com.binarybeasts.sport.models.Client;
import com.binarybeasts.sport.models.Clients;

@RestController
@RequestMapping(path = "/clients")
public class ClientController 
{
    @Autowired
    private ClientDAO clientDao;
    
    @GetMapping(path="/", produces = "application/json")
    public Clients getClients() 
    {
        return clientDao.getAllClients();
    }
    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addClient(
                        @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
                        @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
                        @RequestBody Client client) 
                 throws Exception 
    {       
        //Generate resource id
        Integer id = clientDao.getAllClients().getClientList().size() + 1;
        client.setId(id);
        
        //add resource
        clientDao.addClient(client);
        
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(client.getId())
                                    .toUri();
        
        //Send location in response
        return ResponseEntity.created(location).build();
    }
}
