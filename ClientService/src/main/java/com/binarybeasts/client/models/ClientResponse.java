package com.binarybeasts.client.models;


import javax.persistence.Transient;
import java.util.UUID;

public class ClientResponse extends ClientRequest {
    private Long clientId;

    public ClientResponse() {
        this(null, null, null, 0, null, null);
    }

    public ClientResponse(Long clientId, String id, String fullName, int age, String email, String mobileNumber) {
        super(id, fullName, age, email, mobileNumber);
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}