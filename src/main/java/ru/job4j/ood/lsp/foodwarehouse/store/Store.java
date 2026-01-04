package ru.job4j.ood.lsp.foodwarehouse.store;

import ru.job4j.ood.lsp.foodwarehouse.Food;
import java.util.List;
import java.util.function.Predicate;

public interface Store {

    void add(Food food);

    void remove(Food food);

    List<Food> findBy(Predicate<Food> filter);

    List<Food> findAll();
}
