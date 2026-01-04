package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.formatter.DateTimeParser;
import ru.job4j.ood.srp.reports.model.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ITReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> parser;

    public ITReport(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String ln = System.lineSeparator();
        String sep = ",";

        StringBuilder text = new StringBuilder();
        text.append("Name,Hired,Fired,Salary").append(ln);

        for (Employee e : store.findBy(filter)) {
            text.append(e.getName()).append(sep)
                    .append(parser.parse(e.getHired())).append(sep)
                    .append(parser.parse(e.getFired())).append(sep)
                    .append(e.getSalary())
                    .append(ln);
        }
        return text.toString();
    }
}
