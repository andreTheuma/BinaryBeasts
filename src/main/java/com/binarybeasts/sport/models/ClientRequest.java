package com.binarybeasts.sport.models;

import javax.validation.constraints.*;
import java.util.UUID;

public class ClientRequest {

    // attributes of a new user

    @NotBlank(message = "ID cannot be empty.")
    @Pattern(regexp="(^$|[0-9]{6}[L,M]${1})")
    private String id;

    @NotBlank(message = "Full Name cannot be empty.")
    @Size(max = 120, message = "Full Name is too long.")
    private String fullName;

    @Min(value = 1, message = "value cannot be less tha  1")
    @Max(value = 122, message = "value cannot be more than 122")
    private int age;

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "email must contain @")
    private String email;

    @NotBlank(message = "Mobile Number cannot be empty.")
    @Pattern(regexp="(^$|[0-9]{8})")
    private String mobileNumber;


    public ClientRequest() {
        this(null, null, 0, null, null);
    }

    public ClientRequest(String id, String fullName, int age, String email, String mobileNumber) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.mobileNumber = mobileNumber;
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

}
