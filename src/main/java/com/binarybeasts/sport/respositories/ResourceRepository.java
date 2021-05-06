package com.binarybeasts.sport.respositories;

import com.binarybeasts.sport.models.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {
    
}
