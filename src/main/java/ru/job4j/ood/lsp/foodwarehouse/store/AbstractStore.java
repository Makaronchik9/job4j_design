package ru.job4j.ood.lsp.foodwarehouse.store;

import ru.job4j.ood.lsp.foodwarehouse.Food;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractStore implements Store {
    protected List<Food> foods = new ArrayList<>();

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public void remove(Food food) {
        foods.remove(food);
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return foods.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(foods);
    }
}
