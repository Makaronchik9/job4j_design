package ru.job4j.ood.tdd;

import java.util.Calendar;
import java.util.Objects;

public class Ticket3D implements Ticket {
    private Account account;
    private int row;
    private int column;
    private Calendar date;

    public Ticket3D(Account account, int row, int column, Calendar date) {
        this.account = account;
        this.row = row;
        this.column = column;
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket3D ticket3D = (Ticket3D) o;
        return row == ticket3D.row
                && column == ticket3D.column
                && Objects.equals(account, ticket3D.account)
                && sameDay(this.date, ticket3D.date);
    }

    private boolean sameDay(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, row, column, date);
    }

    @Override
    public String toString() {
        return "Ticket3D{"
                + "account=" + account
                + ", row=" + row
                + ", column=" + column
                + ", date=" + date
                + '}';
    }
}