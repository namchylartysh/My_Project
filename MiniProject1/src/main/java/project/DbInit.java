package project;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class DbInit {
    public static List<Employee> init() {
//        List<Employee> employees = Arrays.asList(
//                new Employee("John", Position.BOSS, 1500, 45),
//                new Employee("Gina", Position.ASSISTANT, 650, 21),
//                new Employee("Bill", Position.ENGINEER, 1050, 35),
//                new Employee("John", Position.ENGINEER, 1100, 35),
//                new Employee("Mike", Position.ENGINEER, 1150, 33)
//        );
//        return employees;



        List<Employee> employees = new ArrayList<>();
        File file = new File("./src/employee.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file));) {
            String st;
            while ((st = br.readLine()) != null) {
                System.out.println(st);
                //employees.add(new Employee(st));
                new ArrayList<>(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;


    }
}
