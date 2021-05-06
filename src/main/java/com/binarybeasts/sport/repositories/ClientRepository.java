package com.binarybeasts.sport.respositories;

import com.binarybeasts.sport.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
