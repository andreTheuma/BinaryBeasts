package com.binarybeasts.client.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ResourceRequest {



    @NotBlank(message = "Resource ID cannot be empty")
    @Pattern(regexp="(^$|[0-9]{6}[L,M]${1})")
    private long resourceId;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Min(value = 0, message = "value cannot be less than 0")
    private double rentalPrice;

    @Min(value = 0, message = "value cannot be less than 0")
    private double purchasePrice;

    @NotBlank(message = "Description cannot be empty")
    private String area;


    public ResourceRequest(String name, double rentalPrice, double purchasePrice, String area){
        this.name = name;
        this.rentalPrice = rentalPrice;
        this.purchasePrice = purchasePrice;
        this.area = area;
    }

    public ResourceRequest(long resourceId,String name, double rentalPrice, double purchasePrice, String area){
        this.resourceId = resourceId;
        this.name = name;
        this.rentalPrice = rentalPrice;
        this.purchasePrice = purchasePrice;
        this.area = area;
    }

    public long getID(){
        return this.resourceId;
    }

    public void setID(long id){
        this.resourceId = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getRentalPrice(){
        return this.rentalPrice;
    }

    public void setRentalPrice(double price){
        this.rentalPrice = price;
    }

    public double getPurchasePrice(){
        return this.purchasePrice;
    }

    public void setPurchasePrice(double price){
        this.purchasePrice = price;
    }

    public String getArea(){
        return this.area;
    }

    public void setArea(String desc){
        this.area = desc;
    }
}