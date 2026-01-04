package ru.job4j.ood.srp.reports.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
public class Employees {

    private List<EmployeeWrapper> employees;

    public Employees() {
    }

    public Employees(List<EmployeeWrapper> employees) {
        this.employees = employees;
    }

    @XmlElement(name = "employee")
    public List<EmployeeWrapper> getEmployees() {
        return employees;
    }
}
