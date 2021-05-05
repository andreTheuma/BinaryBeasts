package com.binarybeasts.sport.services;

import java.util.List;

import com.binarybeasts.sport.models.Resource;

public interface ResourceService {
    List <Resource> getAllResources();
    Resource saveResource(Resource r);
    Resource getResource(long id);
    void deleteResource(long id);

}
