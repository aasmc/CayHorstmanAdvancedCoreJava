package chapter2_io;

import java.time.LocalDate;

public class Employee {
    private String name;
    private double salary;
    private int hireYear;
    private int hireMonth;
    private int hireDay;

    public Employee(String name, double salary, int hireYear, int hireMonth, int hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
        this.hireMonth = hireMonth;
        this.hireYear = hireYear;
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
        return hireYear;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }

    public int getHireMonth() {
        return hireMonth;
    }

    public void setHireMonth(int hireMonth) {
        this.hireMonth = hireMonth;
    }

    public LocalDate getHireDay() {
        return LocalDate.of(hireYear, hireMonth, hireDay);
    }

    public void setHireDay(int hireDay) {
        this.hireDay = hireDay;
    }
}
