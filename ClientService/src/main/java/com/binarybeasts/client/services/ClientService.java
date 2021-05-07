package com.binarybeasts.client.services;


import com.binarybeasts.client.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<Client> saveClient(Client client);

    List<Client> getAllClients();

    Optional<Client> getClientById(Long id);

    void deleteClientById(Long id);
}