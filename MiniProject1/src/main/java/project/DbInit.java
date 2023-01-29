package project;

import java.util.Arrays;
import java.util.List;

public class DbInit {
    public static List<Employee> init() {
        List<Employee> employees = Arrays.asList(
            new Employee("John", Position.BOSS, 1500, 45),
            new Employee("Gina", Position.ASSISTANT, 650, 21),
            new Employee("Bill", Position.ENGINEER, 1050, 35),
            new Employee("John", Position.ENGINEER, 1100, 35),
            new Employee("Mike", Position.ENGINEER, 1150, 33)
        );
        return employees;
    }
}
