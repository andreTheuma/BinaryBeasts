package com.binarybeasts.sport.services;

import com.binarybeasts.sport.models.Client;
import com.binarybeasts.sport.models.ClientEntity;
import com.binarybeasts.sport.respositories.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.RmiClientInterceptorUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    //@Autowired
    //private ClientUtils clientUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Client saveClient(Client client) {
        int age = client.getAge();

        if(age < 14) {
            client.setIsActive(false);
        } else {
            client.setIsActive(true);
        }

        // code to save in the repository
        ClientEntity clientEntity = modelMapper.map(client, ClientEntity.class);
        clientEntity =  clientRepository.save(clientEntity);

        Client savedClient = modelMapper.map(clientEntity, Client.class);

        return client;
    }

    @Override
    public Client getClientById(Long id) {
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        return null;
    }

    @Override
    public void deleteClientById(Long id) {

    }

}
