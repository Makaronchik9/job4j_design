package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.formatter.DateTimeParser;
import ru.job4j.ood.srp.reports.model.Employee;
import ru.job4j.ood.srp.reports.model.EmployeeWrapper;
import ru.job4j.ood.srp.reports.model.Employees;
import ru.job4j.ood.srp.reports.store.Store;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportFormatXml implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> parser;

    public ReportFormatXml(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        try {
            List<EmployeeWrapper> wrappers = store.findBy(filter).stream()
                    .map(e -> new EmployeeWrapper(e, parser))
                    .collect(Collectors.toList());

            Employees employees = new Employees(wrappers);

            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(employees, writer);
            return writer.toString();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
