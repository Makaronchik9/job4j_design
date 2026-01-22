package ru.job4j.ood.lsp.foodwarehouse.store;

import ru.job4j.ood.lsp.foodwarehouse.Food;

import java.util.List;

public interface Store {

    boolean accept(Food food);

    void add(Food food);

    List<Food> findAll();

    void clear();
}
