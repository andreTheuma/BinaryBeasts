package com.binarybeasts.sport.services;

import com.binarybeasts.sport.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client saveClient(Client client);

    List<Client> getAllClients();

    Client getClientById(Long id);

    void deleteClientById(Long id);
}
