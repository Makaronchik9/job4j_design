package ru.job4j.ood.lsp.parking;

public class Car {
    private String automakers;
    private String type;
    private int occupiedCells;

    public Car(String automakers, String type, int occupiedCells) {
        this.automakers = automakers;
        this.type = type;
        this.occupiedCells = occupiedCells;
    }

    public String getAutomakers() {
        return automakers;
    }

    public void setAutomakers(String automakers) {
        this.automakers = automakers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOccupiedCells() {
        return occupiedCells;
    }

    public void setOccupiedCells(int occupiedCells) {
        this.occupiedCells = occupiedCells;
    }
}