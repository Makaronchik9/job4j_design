package ru.job4j.ood.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {

    private final List<Session> sessions = new ArrayList<>();
    private final List<String> soldPlaces = new ArrayList<>();

    @Override
    public List<Session> find(Predicate<Session> filter) {
        List<Session> result = new ArrayList<>();
        for (Session session : sessions) {
            if (filter.test(session)) {
                result.add(session);
            }
        }
        return result;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        if (account == null || date == null || row < 0 || column < 0) {
            throw new IllegalArgumentException();
        }
        String place = row + ":" + column + ":" + date.getTimeInMillis();
        if (soldPlaces.contains(place)) {
            throw new IllegalStateException("Place already taken");
        }
        soldPlaces.add(place);
        return new Ticket3D();
    }

    @Override
    public void add(Session session) {
        sessions.add(session);
    }
}
