package ru.job4j.ood.srp.reports.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.reports.formatter.DateTimeParser;
import ru.job4j.ood.srp.reports.model.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportFormatJson implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> parser;
    private final Gson gson = new GsonBuilder().create();

    public ReportFormatJson(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        return gson.toJson(employees);
    }
}
