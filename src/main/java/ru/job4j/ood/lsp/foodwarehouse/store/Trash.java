package ru.job4j.ood.lsp.foodwarehouse.store;

import ru.job4j.ood.lsp.foodwarehouse.Food;
import java.time.LocalDate;

public class Trash extends AbstractStore {
    @Override
    public void add(Food food) {
        if (food.getExpiryDate().isBefore(LocalDate.now())) {
            super.add(food);
        }
    }
}
