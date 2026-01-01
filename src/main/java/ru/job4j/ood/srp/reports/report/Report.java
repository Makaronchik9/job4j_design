package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.model.Employee;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}
