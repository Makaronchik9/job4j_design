package ru.job4j.ood.lsp.foodwarehouse.store;

import ru.job4j.ood.lsp.foodwarehouse.Food;

public class Trash extends AbstractStore {

    @Override
    public boolean accept(Food food, long totalLife, long remainTime) {
        return remainTime < 0;
    }
}
