package ru.job4j.ood.srp.reports.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.reports.model.Employee;
import ru.job4j.ood.srp.reports.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class HRReportTest {

    @Test
    void whenSortedBySalaryDesc() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();

        Employee first = new Employee("Ivan", now, now, 150_000);
        Employee second = new Employee("Den", now, now, 100_000);

        store.add(first);
        store.add(second);

        Report report = new HRReport(store);

        String expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append("Ivan; 150000.0")
                .append(System.lineSeparator())
                .append("Den; 100000.0")
                .append(System.lineSeparator())
                .toString();

        assertThat(report.generate(e -> true)).isEqualTo(expected);
    }
}
