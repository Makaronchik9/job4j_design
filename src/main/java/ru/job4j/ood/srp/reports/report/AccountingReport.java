package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.currency.Currency;
import ru.job4j.ood.srp.reports.currency.CurrencyConverter;
import ru.job4j.ood.srp.reports.formatter.DateTimeParser;
import ru.job4j.ood.srp.reports.model.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class AccountingReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> parser;
    private final CurrencyConverter converter;

    public AccountingReport(Store store,
                            DateTimeParser<Calendar> parser,
                            CurrencyConverter converter) {
        this.store = store;
        this.parser = parser;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary (USD);")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append("; ")
                    .append(converter.convert(
                            Currency.RUB,
                            employee.getSalary(),
                            Currency.USD))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
