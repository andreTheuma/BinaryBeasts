package com.binarybeasts.client.models;


public class ResourceResponse extends ResourceRequest {

    private long resourceId;

    public ResourceResponse(long resourceId,String name, double rentalPrice, double purchasePrice, String area){
        super(name,rentalPrice,purchasePrice,area);

        this.resourceId = resourceId;
    }

    public long getResourceId(){
        return resourceId;
    }

    public void setResourceId(long resourceId){
        this.resourceId = resourceId;
    }
}
