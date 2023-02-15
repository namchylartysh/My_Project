package project;

import java.io.*;
import java.util.*;


public class DbInit {

    public static List<Employee> init() throws FileNotFoundException {
//        List<Employee> employees = Arrays.asList(
//                new Employee("John", Position.BOSS, 1500, 45),
//                new Employee("Gina", Position.ASSISTANT, 650, 21),
//                new Employee("Bill", Position.ENGINEER, 1050, 35),
//                new Employee("John", Position.ENGINEER, 1100, 35),
//                new Employee("Mike", Position.ENGINEER, 1150, 33)
//        );
//        return employees;

        List<Employee> employees = new ArrayList<>();
        File file = new File("./src/main/resources/employees.txt");
        List<String[]> list = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] newEmployee = line.split(", ");
            System.out.println(Arrays.toString(newEmployee));
            list.add(newEmployee);
            Employee temp = new Employee(newEmployee);
            employees.add(temp);
        }
        return employees;

        //        List<Employee> employees = new ArrayList<>();
//        File file = new File("./src/main/resources/employees.txt");
//        try (BufferedReader br = new BufferedReader(new FileReader(file));) {
//            String line;
//            Scanner scanner;
//            int index = 0;
//            while ((line = br.readLine()) != null) {
//                Employee employee = new Employee();
//                scanner = new Scanner(line);
//                scanner.useDelimiter(",");
//                while (scanner.hasNext()) {
//                    String data = scanner.next();
//                    if (index == 0) {
//                        employee.setName(data);
//                    }
//                    if (index == 1) {
//                        employee.setPosition(Position.valueOf(data));
//                    }
//                    if (index == 2) {
//                        employee.setSalary(Integer.parseInt(data));
//                    }
//                    if (index == 3) {
//                        employee.setAge(Integer.parseInt(data));
//                    } else {
//                        System.out.println("Failure data:" + data);
//                    }
//                    index++;
//                }
//                employees.add(employee);
//                br.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return employees;
    }
}
