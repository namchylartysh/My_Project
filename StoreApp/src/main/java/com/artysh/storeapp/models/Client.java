package com.artysh.storeapp.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty(message = "This field should not be empty")
    @Size(min = 2, max = 100, message = "Name should be more than 2 and less than 100")
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "first_name")
    @NotEmpty(message = "Please enter your first name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Please enter your last name")
    private String lastName;

    @Min(value = 1920, message = "Date of birth can not be earlier than 1900")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Email
    @Size(min=4, max=50)
    @NotBlank(message = "Please enter your valid email")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "role")
    private String role;

    public Client() {

    }

    public Client(int clientId, String password, String username, String firstName, String lastName, int yearOfBirth, String email, String phoneNumber, String role) {
        this.clientId = clientId;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
