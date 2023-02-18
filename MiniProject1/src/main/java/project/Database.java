package project;

import java.io.*;
import java.util.*;

public class Database {
    static List<Employee> employees;
    Map<Integer, Employee> indexMap;

    public Database(List<Employee> employees) {
        this.employees = new ArrayList<>(employees);
        this.indexMap = new HashMap<>();
        employees.forEach(employee -> {indexMap.put(employee.getId(), employee);});
    }

    public void create() {
        Employee employee = DataUtil.getEmployee("create: ");
        create(employee);

    }

    public void create(Employee employee) {
        if (employee != null) {
            employees.add(employee);
            indexMap.put(employee.getId(), employee);
            System.out.println("Added " + employee);
        }
    }

    public void read() {
        DataUtil.print(employees);
    }

    public void find() {
        Selection sn = new Selection(employees);
        System.out.println("search by: name, position, salary, age");
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("## ");
                String enter = scanner.next();
                switch (enter.toLowerCase()) {
                    case "name":
                        sn.selectByName();
                        break;
                    case "position":
                        sn.selectByPosition();
                        break;
                    case "salary":
                        sn.selectBySalary();
                        break;
                    case "age":
                        sn.selectByAge();
                        break;
                    case "exit":
                        System.out.println("Search finished");
                        return;
                    default:
                        System.out.println("This criteria is not found");
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private Employee findById(int id) {
        return indexMap.get(id);
    }

    public void update() {
        int id = DataUtil.getInt("update, find by id: ");
        Employee employee = findById(id);
        if (employee != null) {
            Employee tmp = DataUtil.getEmployeePart("update (position, salary, age): ");
            if (tmp != null) {
                employee.update(tmp.getPosition(), tmp.getSalary(), tmp.getAge());
                System.out.println("Updated " + employee);
            }
        }
    }

    public void delete() {
        int id = DataUtil.getInt("remove, find by id: ");
        Employee employee = findById(id);
        if (employee != null) {
            employees.remove(employee);
            indexMap.remove(employee.getId());
            System.out.println("Deleted " + employee);
        }
    }

    public void sort() {
        String sortName = DataUtil.getString("sort: n[ame], p[osition], s[alary], a[ge]: ");
        Comparator<Employee> comparator;
        switch (sortName.toLowerCase().charAt(0)) {
            case 'n':
                comparator = Comparator.comparing(Employee::getName);
                break;
            case 'p':
                comparator = Comparator.comparing(Employee::getPosition);
                break;
            case 's':
                comparator = Comparator.comparingInt(Employee::getSalary);
                break;
            case 'a':
                comparator = Comparator.comparingInt(Employee::getAge);
                break;
            default:
                return;
//            case 'n':
//                comparator = (o1, o2) -> o1.getName().compareTo(o2.getName());
//                break;
//            case 'p':
//                comparator = (o1, o2) -> o1.getPosition().compareTo(o2.getPosition());
//                break;
//            case 's':
//                comparator = (o1, o2) -> o1.getSalary() - o2.getSalary();
//                break;
//            case 'a':
//                comparator = (o1, o2) -> o1.getAge() - o2.getAge();
//                break;
//            default:
//                return;
        }
        List<Employee> sortedList = new ArrayList<>(employees);
        sortedList.sort(comparator);
        DataUtil.print(sortedList);

    }

    public void group() {
        Grouper gr = new Grouper(employees);
        System.out.println("group by: [n]ames, [p]ositions, [s]alaries, [a]ges, e[x]it");
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("## ");
                String enter = scanner.next();
                switch (enter.toLowerCase().charAt(0)) {
                    case 'n':
                        gr.groupByName();
                        break;
                    case 'p':
                        gr.groupByPosition();
                        break;
                    case 's':
                        gr.groupBySalary();
                        break;
                    case 'a':
                        gr.groupByAge();
                        break;
                    case 'x':
                        System.out.println("Grouping finished");
                        return;
                    default:
                        System.out.println("This command is not found");
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        List<Employee> employeeList = new ArrayList<>(employees);
        File file = new File("./src/main/resources/new_employees.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(employeeList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Successfully wrote to the file");
        }

    }
}
