package chapter2_io;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
    static final long RECORD_SIZE = 100L;
    static final int NAME_SIZE = 40;
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee() {
        this.name = "";
        this.salary = 0;
        this.hireDay = LocalDate.of(1990, 1, 1);
    }

    public Employee(String name, double salary, int hireYear, int hireMonth, int hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(hireYear, hireMonth, hireDay);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getHireYear() {
        return hireDay.getYear();
    }

    public int getHireMonth() {
        return hireDay.getMonthValue();
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
