package main.java.entities;

public class Empleado {
    public Empleado(final String name, final String lastName, final String cargo, final Double salary) {
        this.name = name;
        this.lastName = lastName;
        this.cargo = cargo;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalary() {
        return salary;
    }
    
    private String name;
    private String lastName;
    private String cargo;
    private Double salary;
}
