package chapter2_io;

public class Manager extends Employee {

    private Employee secretary;

    public Manager(String name, double salary, int hireYear, int hireMonth, int hireDay) {
        super(name, salary, hireYear, hireMonth, hireDay);
    }

    public Employee getSecretary() {
        return secretary;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }
}
