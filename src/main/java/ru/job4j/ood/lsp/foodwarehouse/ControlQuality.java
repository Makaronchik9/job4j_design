package ru.job4j.ood.lsp.foodwarehouse;

import ru.job4j.ood.lsp.foodwarehouse.store.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void choosePlace(Food food) {
        for (Store store : stores) {
            if (store.accept(food)) {
                store.add(food);
                return;
            }
        }
    }

    public void resort() {
        List<Food> allFoods = new ArrayList<>();

        for (Store store : stores) {
            allFoods.addAll(store.findAll());
            store.clear();
        }

        for (Food food : allFoods) {
            choosePlace(food);
        }
    }
}
