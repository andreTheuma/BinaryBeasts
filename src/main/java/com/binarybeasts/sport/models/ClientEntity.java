package com.binarybeasts.sport.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue
    private Long clientId; // set as primary key

    private String id;
    private String fullName;
    private int age;
    private String email;
    private String mobileNumber;
    private Boolean isActive;

    public ClientEntity() {
        this(null, null, null, 0, null, null, null);
    }

    public ClientEntity(String id, String fullName, int age, String email, String mobileNumber, Boolean isActive) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.setIsActive(isActive);
    }

    public ClientEntity(Long clientId, String id, String fullName, int age, String email, String mobileNumber, Boolean isActive)  {
        this.clientId = clientId;
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.setIsActive(isActive);
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
