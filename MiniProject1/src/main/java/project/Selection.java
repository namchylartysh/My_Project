package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Selection {

    static List<Employee> employees;
    Map<Integer, Employee> indexMap;
    public Selection(List<Employee> employees) {
        Selection.employees = new ArrayList<>(employees);
        this.indexMap = new HashMap<>();
        for (Employee employee : employees) {
                indexMap.put(employee.getId(), employee);
        }
    }

    public void selectByPosition() {
        System.out.print("position: ");
        Position position = DataUtil.getPosition();
        List<Employee> found = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getPosition().equals(position)) {
                found.add(employee);
            }
        }
        DataUtil.print(found);
    }

    public void selectByName() {
        String name = DataUtil.getString("name: ");
        List<Employee> found = new ArrayList<>();
        for (Employee employee : employees) {
           if (employee.getName().contains(name)) {
               found.add(employee);
           }
        }
        DataUtil.print(found);
    }

    public void selectBySalary() {
        int salary = DataUtil.getInt("salary: ");
        List<Employee> found = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary() == (salary)) {
                found.add(employee);
            }
        }
        DataUtil.print(found);
    }

    public void selectByAge() {
        int age = DataUtil.getInt("age: ");
        List<Employee> found = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() == (age)) {
                found.add(employee);
            }
        }
        DataUtil.print(found);
    }
}
