package ru.job4j.ood.lsp.foodwarehouse;

import ru.job4j.ood.lsp.foodwarehouse.store.AbstractStore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ControlQuality {

    private final AbstractStore warehouse;
    private final AbstractStore shop;
    private final AbstractStore trash;

    public ControlQuality(AbstractStore warehouse, AbstractStore shop, AbstractStore trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void chosePlace(Food food) {
        long totalLife = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long remainTime = ChronoUnit.DAYS.between(LocalDate.now(), food.getExpiryDate());

        if (remainTime > totalLife * 0.75) {
            warehouse.add(food);
        } else if (remainTime >= 0 && remainTime <= totalLife * 0.75) {
            shop.add(food);
        } else {
            trash.add(food);
        }
    }
}
