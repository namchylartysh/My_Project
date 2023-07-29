package com.artysh.storeapp.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "store_users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

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
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "role")
    private String role;

    public User() {

    }

    public User(int userId, String password, String username, String firstName, String lastName, int yearOfBirth, String email, String phoneNumber, String role) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

}
