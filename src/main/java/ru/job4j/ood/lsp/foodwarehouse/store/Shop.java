package ru.job4j.ood.lsp.foodwarehouse.store;

import ru.job4j.ood.lsp.foodwarehouse.Food;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Shop extends AbstractStore {
    @Override
    public void add(Food food) {
        long totalLife = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long remainTime = ChronoUnit.DAYS.between(LocalDate.now(), food.getExpiryDate());
        if (remainTime <= totalLife * 0.24) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() / 100.0);
        }
        super.add(food);
    }
}
