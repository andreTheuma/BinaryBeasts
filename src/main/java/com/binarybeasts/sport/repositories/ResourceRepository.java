package com.binarybeasts.sport.repositories;

import com.binarybeasts.sport.models.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {
    
}
