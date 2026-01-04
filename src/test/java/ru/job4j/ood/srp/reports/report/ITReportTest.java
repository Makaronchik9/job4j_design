package ru.job4j.ood.srp.reports.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.reports.formatter.DateTimeParser;
import ru.job4j.ood.srp.reports.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.reports.model.Employee;
import ru.job4j.ood.srp.reports.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ITReportTest {

    @Test
    void whenGeneratedCSV() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100_000);

        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);

        Report report = new ITReport(store, parser);

        String expected = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(",")
                .append(parser.parse(worker.getHired())).append(",")
                .append(parser.parse(worker.getFired())).append(",")
                .append(worker.getSalary())
                .append(System.lineSeparator())
                .toString();

        assertThat(report.generate(e -> true)).isEqualTo(expected);
    }
}
