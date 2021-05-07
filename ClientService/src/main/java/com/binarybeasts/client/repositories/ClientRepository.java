package com.binarybeasts.client.repositories;


import com.binarybeasts.client.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}