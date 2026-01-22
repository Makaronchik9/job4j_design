package ru.job4j.ood.lsp.foodwarehouse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Food {

    private final String name;
    private final LocalDate createDate;
    private final LocalDate expiryDate;
    private int price;
    private final int discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, int price, int discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public double getLifePercent() {
        long total = ChronoUnit.DAYS.between(createDate, expiryDate);
        long passed = ChronoUnit.DAYS.between(createDate, LocalDate.now());
        return (double) passed / total * 100;
    }

    public void applyDiscount() {
        price = price - price * discount / 100;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
