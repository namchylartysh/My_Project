package online.bank.app.controllers.vo;

import online.bank.app.models.Role;
import online.bank.app.models.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserVO {

    private int user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String token;
    private LocalDate verified_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public UserVO(int user_id, String firstName, String lastName, String email, String password, Role role, String token, LocalDate verified_at, LocalDateTime created_at, LocalDateTime updated_at) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = token;
        this.verified_at = verified_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public LocalDate getVerified_at() {
        return verified_at;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public static UserVO valueOf(User user) {
        return new UserVO(user.getUser_id(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole(), user.getToken(), user.getVerified_at(), user.getCreated_at(), user.getUpdated_at());
    }
}
