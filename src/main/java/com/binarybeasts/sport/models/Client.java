package com.binarybeasts.sport.models;

public class Client {

    private Long clientId;
    private String id;
    private String fullName;
    private int age;
    private String email;
    private String mobileNumber;

    private Boolean isActive;

    public Client() {
        this(null, null, 0, null, null);
    }

    public Client(String id, String fullName, int age, String email, String mobileNumber) {
        this(null, id, fullName, age, email, mobileNumber);
    }

    public Client(Long clientId, String id, String fullName, int age, String email, String mobileNumber) {
        this.setClientId(clientId);
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.mobileNumber = mobileNumber;
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
