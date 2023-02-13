package project;

import java.util.*;

public class Database {
    List<Employee> employees;
    Map<Integer, Employee> indexMap;

    public Database(List<Employee> employees) {
        this.employees = new ArrayList<>(employees);
        this.indexMap = new HashMap<>();
        employees.stream().forEach(employee -> {indexMap.put(employee.getId(), employee);});
//        for (Employee employee : employees) {
//            indexMap.put(employee.getId(), employee);
//        }
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
        String name = DataUtil.getString("find: ");
        List<Employee> found = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().contains(name)) {
                found.add(employee);
            }
        }
        DataUtil.print(found);
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

    public void positions() {
        List<Position> positions = new ArrayList<>();
        employees.stream().forEach(employee -> {positions.add(employee.getPosition());});
//        for (Employee employee : employees) {
//            positions.add(employee.getPosition());
//        }
        System.out.println(new HashSet<>(positions));
    }

    public void sort() {
        String sortName = DataUtil.getString("sort: n[ame], p[osition], s[alary], a[ge]: ");
        Comparator<Employee> comparator;
        switch (sortName.toLowerCase().charAt(0)) {
            case 'n':
                comparator = (o1, o2) -> o1.getName().compareTo(o2.getName());
                break;
            case 'p':
                comparator = (o1, o2) -> o1.getPosition().compareTo(o2.getPosition());
                break;
            case 's':
                comparator = (o1, o2) -> o1.getSalary() - o2.getSalary();
                break;
            case 'a':
                comparator = (o1, o2) -> o1.getAge() - o2.getAge();
                break;
            default:
                return;
        }
        List<Employee> sortedList = new ArrayList<>(employees);
        sortedList.sort(comparator);
        DataUtil.print(sortedList);

    }
}
