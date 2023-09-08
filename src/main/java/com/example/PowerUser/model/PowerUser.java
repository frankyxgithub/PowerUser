package com.example.PowerUser.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "power_users", schema = "powerful_users", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "email"}))
public class PowerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int id;

    @NotBlank
    @Length(min = 6, max = 30, message = "Name should be at least 6 characters")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Email(message = "Please enter a valid email address")
    private String email;


    @NotEmpty
    private String address;

    @Min(value = 18, message = "Age should be less than 18")
    @Max(value = 65, message = "Age should not be more than 65")
    private int age;

    public PowerUser(){
    }

    public PowerUser(int id, String fullName, String email, String address, int age) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
