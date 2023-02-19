package project;

import java.util.*;

public class Grouper {
    static List<Employee> employees;
    Map<Integer, Employee> indexMap;
    public Grouper(List<Employee> employees) {
        Grouper.employees = new ArrayList<>(employees);
        this.indexMap = new HashMap<>();
        for (Employee employee : employees) {
            indexMap.put(employee.getId(), employee);
        }
    }


    public void groupByName() {
        List<String> names = new ArrayList<>();
        employees.forEach(employee -> {names.add(employee.getName());});
        System.out.println(new HashSet<>(names));
    }

    public void groupByPosition() {
        List<String> positions = new ArrayList<>();
        employees.forEach(employee -> {positions.add(employee.getPosition());});
        System.out.println(new HashSet<>(positions));
    }

    public void groupBySalary() {
        List<Integer> salaries = new ArrayList<>();
        employees.forEach(employee -> {salaries.add(employee.getSalary());});
        System.out.println(new HashSet<>(salaries));
    }

    public void groupByAge() {
        List<Integer> ages = new ArrayList<>();
        employees.forEach(employee -> {ages.add(employee.getAge());});
        System.out.println(new HashSet<>(ages));
    }
}
