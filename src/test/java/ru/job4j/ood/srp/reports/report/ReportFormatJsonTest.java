package ru.job4j.ood.srp.reports.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.reports.formatter.DateTimeParser;
import ru.job4j.ood.srp.reports.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.reports.model.Employee;
import ru.job4j.ood.srp.reports.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportFormatJsonTest {

    @Test
    void whenGenerateJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Ivan", now, now, 100000);
        Employee emp2 = new Employee("Den", now, now, 150000);

        store.add(emp1);
        store.add(emp2);

        DateTimeParser<Calendar> parser = new ReportDateTimeParser();

        Report report = new ReportFormatJson(store, parser);
        String json = report.generate(e -> true);

        assertThat(json).contains("Ivan");
        assertThat(json).contains("Den");
        assertThat(json).contains("100000");
        assertThat(json).contains("150000");

        assertThat(json).contains("name");
        assertThat(json).contains("salary");
        assertThat(json).contains("hired");
        assertThat(json).contains("fired");
    }
}
