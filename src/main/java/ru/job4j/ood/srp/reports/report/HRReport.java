package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.model.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.Comparator;
import java.util.function.Predicate;

public class HRReport implements Report {

    private final Store store;

    public HRReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());

        store.findBy(filter).stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .forEach(e -> text.append(e.getName())
                        .append("; ")
                        .append(e.getSalary())
                        .append(System.lineSeparator()));

        return text.toString();
    }
}
