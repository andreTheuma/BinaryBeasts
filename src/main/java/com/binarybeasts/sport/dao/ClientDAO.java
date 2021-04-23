package com.binarybeasts.sport.dao;

import org.springframework.stereotype.Repository;

import com.binarybeasts.sport.models.Client;
import com.binarybeasts.sport.models.Clients;

@Repository
public class ClientDAO 
{
    private static Clients list = new Clients();
    
    static 
    {
        list.getClientList().add(new Client(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com"));
        list.getClientList().add(new Client(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
        list.getClientList().add(new Client(3, "David", "Kameron", "titanic@gmail.com"));
    }
    
    public Clients getAllClients() 
    {
        return list;
    }
    
    public void addClient(Client client) {
        list.getClientList().add(client);
    }
}
