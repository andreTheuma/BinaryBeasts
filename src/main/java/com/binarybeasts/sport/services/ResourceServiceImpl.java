package com.binarybeasts.sport.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.reflect.Type;
import java.util.List;


import com.binarybeasts.sport.models.BookingEntity;
import com.binarybeasts.sport.models.Resource;
import com.binarybeasts.sport.models.ResourceEntity;
import com.binarybeasts.sport.models.ResourceRequest;
import com.binarybeasts.sport.models.ResourceResponse;
import com.binarybeasts.sport.repositories.ResourceRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private ResourceRepository resourceRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Resource> getAllResources() {
        
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<ResourceEntity> entities = resourceRepo.findAll();
        List<Resource> resources = new ArrayList<>();
        for (int i = 0;i < entities.size();i++)
            resources.add(modelMapper.map(entities.get(i), Resource.class));
        return resources;
    }

    @Override
    public Resource saveResource(Resource r) {
        ResourceEntity resourceEntity = modelMapper.map(r, ResourceEntity.class);

        resourceEntity =  resourceRepo.save(resourceEntity);

        Resource savedResource = modelMapper.map(resourceEntity, Resource.class);

        return savedResource;
    }

    @Override
    public Resource getResource(long id) {
        
        Optional<ResourceEntity> resourceEntityInDb = resourceRepo.findById(id);

        if (!resourceEntityInDb.isPresent()){
            return null;
        }

        ResourceEntity resourceEntity = resourceEntityInDb.get();

        Resource resource = modelMapper.map(resourceEntity, Resource.class);

        return resource;
    }

    @Override
    public void deleteResource(long id) {
        resourceRepo.deleteById(id);
    }
    
}
