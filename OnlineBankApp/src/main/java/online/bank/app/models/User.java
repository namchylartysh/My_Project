package online.bank.app.models;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name = "first_name")
    @NotEmpty(message = "Please enter your first name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Please enter your last name")
    private String lastName;

    @Email
    @Size(min=4, max=50)
    @NotBlank(message = "Please enter your valid email")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Please enter correct password")
    @Size(min=6, message = "This field must be more than 6 symbols")
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(int user_id, String firstName, String lastName, String email, String password, Role role) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
