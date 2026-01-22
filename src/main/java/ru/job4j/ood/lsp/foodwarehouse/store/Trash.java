package ru.job4j.ood.lsp.foodwarehouse.store;

import ru.job4j.ood.lsp.foodwarehouse.Food;

public class Trash extends AbstractStore {

    @Override
    public boolean accept(Food food) {
        return food.getLifePercent() >= 100;
    }
}
