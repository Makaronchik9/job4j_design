package ru.job4j.ood.srp.reports.model;

import ru.job4j.ood.srp.reports.formatter.DateTimeParser;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.Calendar;

public class EmployeeWrapper {

    private Employee employee;
    private String hired;
    private String fired;

    public EmployeeWrapper(Employee employee, DateTimeParser<Calendar> parser) {
        this.employee = employee;
        this.hired = parser.parse(employee.getHired());
        this.fired = parser.parse(employee.getFired());
    }

    @XmlElement
    public String getName() {
        return employee.getName();
    }

    @XmlElement
    public double getSalary() {
        return employee.getSalary();
    }

    @XmlElement
    public String getHired() {
        return hired;
    }

    @XmlElement
    public String getFired() {
        return fired;
    }
}
