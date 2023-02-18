package project;

import com.sun.source.tree.BreakTree;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;


public class DbInit {

  //  static String filePath = "./src/main/resources/employees.txt";

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
            //System.out.println(Arrays.toString(newEmployee));
            list.add(newEmployee);
            Employee temp = new Employee(newEmployee);
            employees.add(temp);
        }
        return employees;
    }

//    public static List<Employee> updateDb(List<Employee> data) {
//        List<Employee> employees = new ArrayList<>();
//        File file = new File(filePath);
//        try {
//            FileWriter fr = null;
//            fr = new FileWriter(file);
//            fr.write(data.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return employees;
//    }

}
