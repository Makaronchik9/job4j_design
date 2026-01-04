package ru.job4j.ood.srp.reports.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.reports.formatter.DateTimeParser;
import ru.job4j.ood.srp.reports.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.reports.model.Employee;
import ru.job4j.ood.srp.reports.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportFormatXmlTest {

    @Test
    void whenGenerateXml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee emp = new Employee("Ivan", now, now, 100000);
        store.add(emp);

        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report report = new ReportFormatXml(store, parser);

        String xml = report.generate(e -> true);

        assertThat(xml).isNotNull();
        assertThat(xml).startsWith("<?xml");
        assertThat(xml).contains("Ivan");
        assertThat(xml).contains("100000");
    }
}
