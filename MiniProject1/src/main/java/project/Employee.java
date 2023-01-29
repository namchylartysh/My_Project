package project;

public class Employee {
    private int id;
    private String name;
    private Position position;
    private int salary;
    private int age;
    private static int count = 0;

    public Employee(String name, Position position, int salary, int age) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.age = age;
        this.id = ++count;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public void update(Position position, int salary, int age) {
        this.position = position;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
