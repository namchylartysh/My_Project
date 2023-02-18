package project;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class DbInit {
    public static String pathName = "./src/main/resources/employees.txt";

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
        File file = new File(pathName);
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





/*
    private static void appendUsingOutputStream(String fileName, String data) {
        OutputStream os = null;
        try {
            //в конструкторе FileOutputStream используем флаг true, который обозначает обновление содержимого файла
            os = new FileOutputStream(new File(fileName), true);
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
     */



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
