package ru.job4j.ood.tdd;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    private int minRows = 1;
    private int maxRows = 11;
    private int minColumns = 1;
    private int maxColumns = 11;
    private List<Session> sessions = new ArrayList<>();

    @Override
    public List<Session> find(Predicate<Session> filter) {
        List<Session> rsl = new ArrayList<>();
        for (Session session: sessions) {
            if (filter.test(session)) {
                rsl.add(session);
            }
        }
        return rsl;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) throws IllegalArgumentException {
        if (row < minRows || row > maxRows || column < minColumns || column > maxColumns) {
            throw new IllegalArgumentException("Вы выбрали недоступное место");
        }
        return new Ticket3D(account, row, column, date);
    }

    @Override
    public void add(Session session) {
        sessions.add(session);
    }
}