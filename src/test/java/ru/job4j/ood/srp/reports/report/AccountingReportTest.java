package ru.job4j.ood.srp.reports.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.reports.currency.Currency;
import ru.job4j.ood.srp.reports.currency.CurrencyConverter;
import ru.job4j.ood.srp.reports.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.reports.formatter.DateTimeParser;
import ru.job4j.ood.srp.reports.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.reports.model.Employee;
import ru.job4j.ood.srp.reports.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class AccountingReportTest {

    @Test
    void whenSalaryConvertedToUSD() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100_000);

        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();

        store.add(worker);

        Report report = new AccountingReport(store, parser, converter);

        String expected = new StringBuilder()
                .append("Name; Salary (USD);")
                .append(System.lineSeparator())
                .append(worker.getName()).append("; ")
                .append(converter.convert(
                        Currency.RUB,
                        worker.getSalary(),
                        Currency.USD))
                .append(System.lineSeparator())
                .toString();

        assertThat(report.generate(e -> true)).isEqualTo(expected);
    }
}
