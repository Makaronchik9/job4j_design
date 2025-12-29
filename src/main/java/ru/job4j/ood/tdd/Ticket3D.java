package ru.job4j.ood.tdd;

import java.util.Objects;

public class Ticket3D implements Ticket {

    private final int row;
    private final int column;

    public Ticket3D() {
        this.row = 0;
        this.column = 0;
    }

    public Ticket3D(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket3D)) return false;
        Ticket3D ticket3D = (Ticket3D) o;
        return row == ticket3D.row && column == ticket3D.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
