package ru.job4j.ood.lsp.foodwarehouse;

import ru.job4j.ood.lsp.foodwarehouse.store.Store;
import ru.job4j.ood.lsp.foodwarehouse.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality {

    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void chosePlace(Food food) {
        long totalLife = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long remainTime = ChronoUnit.DAYS.between(LocalDate.now(), food.getExpiryDate());

        for (Store store : stores) {
            if (store.accept(food, totalLife, remainTime)) {
                store.add(food);
                break;
            }
        }
    }
}
