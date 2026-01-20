package ru.job4j.ood.lsp.foodwarehouse.store;

import ru.job4j.ood.lsp.foodwarehouse.Food;

public class Shop extends AbstractStore {

    @Override
    public boolean accept(Food food, long totalLife, long remainTime) {
        boolean result = remainTime >= 0 && remainTime <= totalLife * 0.75;
        if (result && remainTime <= totalLife * 0.25) {
            food.setPrice(
                    food.getPrice() - food.getPrice() * food.getDiscount() / 100.0
            );
        }
        return result;
    }
}
