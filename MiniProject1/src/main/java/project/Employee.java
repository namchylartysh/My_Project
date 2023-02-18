package project;

public class Employee {
    private int id;
    private String name;
    private String position;
    private int salary;
    private int age;
    private static int count = 0;

    public Employee(String name, String position, int salary, int age) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.age = age;
        this.id = ++count;
    }

    public Employee(String[] x) {
        if (x.length == 4) {
            this.name = x[0];
            this.position = (x[1]);
            this.salary = Integer.parseInt(x[2]);
            this.age = Integer.parseInt(x[3]);
            this.id = ++count;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public void update(String position, int salary, int age) {
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
