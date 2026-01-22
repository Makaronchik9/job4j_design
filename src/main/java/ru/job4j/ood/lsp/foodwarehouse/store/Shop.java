package ru.job4j.ood.lsp.foodwarehouse.store;

import ru.job4j.ood.lsp.foodwarehouse.Food;

public class Shop extends AbstractStore {

    @Override
    public boolean accept(Food food) {
        double percent = food.getLifePercent();
        if (percent >= 25 && percent < 75) {
            return true;
        }
        if (percent >= 75 && percent < 100) {
            food.applyDiscount();
            return true;
        }
        return false;
    }
}
