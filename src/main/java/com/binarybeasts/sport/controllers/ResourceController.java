package com.binarybeasts.sport.controllers;

import com.binarybeasts.sport.services.ResourceService;
import com.binarybeasts.sport.models.Resource;
import com.binarybeasts.sport.models.ResourceRequest;
import com.binarybeasts.sport.models.ResourceResponse;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
public class ResourceController {
    
    @Autowired
    ResourceService resourceService;

    @Autowired
    ModelMapper modelMapper;

    //user crud operations

    @GetMapping("/allResources")
    public List<ResourceResponse> findAll() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<Resource> resources = resourceService.getAllResources();
        List<ResourceResponse> resourceList = new ArrayList<ResourceResponse>();

        for (int i = 0;i < resources.size();i++){
            Resource r = resources.get(i);
            ResourceResponse resourceResponse = modelMapper.map(r, ResourceResponse.class);
            resourceList.add(resourceResponse);
        }

        return resourceList;

    }

    @PostMapping("/resource")
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceResponse addResource(@Valid @RequestBody ResourceRequest resourceRequest){
        
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Resource r = modelMapper.map(resourceRequest, Resource.class);

        Resource savedResource = resourceService.saveResource(r);
        
        ResourceResponse resourceResponse = modelMapper.map(savedResource, ResourceResponse.class);
        
        return resourceResponse;

    }

    @GetMapping("/resource/{id}")
    public ResourceResponse findOne(@PathVariable long resourceId) {
        Resource resource = resourceService.getResource(resourceId);

        ResourceResponse resourceResponse = modelMapper.map(resource, ResourceResponse.class);

        return resourceResponse;
    }

    @PutMapping("/client/{id}")
    public ResourceResponse saveOrUpdate(@RequestBody ResourceRequest resourceRequest, @PathVariable long resourceId) {

        //TODO add exceptions
       Resource existingResource = resourceService.getResource(resourceId);//.orElseThrow(() -> new ClientNotFoundException(clientId));

        Resource newResource = modelMapper.map(resourceRequest, Resource.class);

        newResource.setID(existingResource.getID());

        Resource updatedResource = resourceService.saveResource(newResource);//.orElseThrow(() -> new ClientInvalidException());

        ResourceResponse resourceResponse = modelMapper.map(updatedResource, ResourceResponse.class);

        return resourceResponse;
    }

    

    @DeleteMapping("/resource/{id}")
    void deleteResource(@PathVariable long resourceId) {

        resourceService.deleteResource(resourceId);
    }
}
