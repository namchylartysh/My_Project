package projectTest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import project.Database;
import project.Employee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static project.Database.create;

public class DatabaseTest {
    public static List<Employee> employees = Arrays.asList(
            new Employee("Albert", "Boss", 5000, 35),
                new Employee("Max", "ENGINEER", 3500, 37),
                new Employee("Elizabeth", "ENGINEER", 3500, 32),
                new Employee("Karim", "ENGINEER", 3200, 33),
                new Employee("Johannes", "ASSISTANT", 2500, 29),
                new Employee("Kira", "ASSISTANT", 2400, 26),
                new Employee("Fred", "DESIGNER", 2900, 30),
                new Employee("Tim", "TESTER", 2600, 25)
        );
    public Database db;

    @BeforeEach
    public void init() {
        db = new Database(employees);
    }

    public static Stream<Arguments> dataForCreateTest() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new Employee("William", "CLEANER", 1200, 50)));
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("dataForCreateTest")
    public void createTest(Employee emp) {
        Employee found = create(emp);
        Assertions.assertEquals(found.getName(), emp.getName());
        Assertions.assertEquals(found.getPosition(), emp.getPosition());
        Assertions.assertEquals(found.getSalary(), emp.getSalary());
        Assertions.assertEquals(found.getAge(), emp.getAge());
    }

    public static Stream<Arguments> dataForFindById() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(1, employees.get(0), true));
        out.add(Arguments.arguments(3, employees.get(4), false));
        return out.stream();
    }


    @ParameterizedTest
    @MethodSource("dataForFindById")
    public void findByIdTest(int item, Employee employee, boolean result) {
        Employee found = db.findById(item);
        Assertions.assertEquals(result, employee.equals(found));
    }

    public static Stream<Employee> dataForReadTest() {
        List<Employee> out = new ArrayList<>(employees);
        return out.stream();
    }


    @ParameterizedTest
    @MethodSource("dataForReadTest")
    public void readTest() {
        List<Employee> result = Database.read();
        Assertions.assertEquals(result, employees);
    }

}
