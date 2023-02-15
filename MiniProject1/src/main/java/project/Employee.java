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

    public Employee(String[] args) {
        if (args.length == 4) {
            this.name = args[0];
            this.position = Position.valueOf(args[1]);
            this.salary = Integer.parseInt(args[2]);
            this.age = Integer.parseInt(args[3]);
            this.id = ++count;

        }
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
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
