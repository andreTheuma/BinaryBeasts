package com.binarybeasts.sport.models;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ResourceEntity {
    
    @Id
    @GeneratedValue
    private long resourceId;

    private String name;
    private double rentalPrice;
    private double purchasePrice;
    private String area;

    public ResourceEntity(String name, double rentalPrice, double purchasePrice, String area){
        this.name = name;
        this.rentalPrice = rentalPrice;
        this.purchasePrice = purchasePrice;
        this.area = area;
    }

    public ResourceEntity(long resourceId,String name, double rentalPrice, double purchasePrice, String area){
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
