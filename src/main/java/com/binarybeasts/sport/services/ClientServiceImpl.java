package com.binarybeasts.sport.services;

import com.binarybeasts.sport.models.Client;
import com.binarybeasts.sport.models.ClientEntity;
import com.binarybeasts.sport.respositories.ClientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.RmiClientInterceptorUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
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
    public Optional<Client> saveClient(Client client) {
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

        return Optional.of(savedClient);
    }

    @Override
    public Optional<Client> getClientById(Long clientId) {
        Optional<ClientEntity> clientEntityInDb = clientRepository.findById(clientId);

        if (clientEntityInDb.isEmpty()){
            return Optional.empty();
        }

        ClientEntity clientEntity = clientEntityInDb.get();

        Client client = modelMapper.map(clientEntity, Client.class);

        return Optional.of(client);
    }

    @Override
    public List<Client> getAllClients() {
        List<ClientEntity> userEntities = clientRepository.findAll();

        Type returnType = new TypeToken<List<Client>>() {}.getType();

        List<Client> clients = modelMapper.map(userEntities, returnType);

        return clients;
    }

    @Override
    public void deleteClientById(Long clientId) {
        clientRepository.deleteById(clientId);
    }

}
