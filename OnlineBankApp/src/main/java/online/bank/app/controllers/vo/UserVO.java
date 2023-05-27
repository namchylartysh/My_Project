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


    public UserVO(int user_id, String firstName, String lastName, String email, String password, Role role) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public static UserVO valueOf(User user) {
        return new UserVO(user.getUser_id(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
