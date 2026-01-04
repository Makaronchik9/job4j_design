package ru.job4j.ood.lsp.foodwarehouse.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodwarehouse.ControlQuality;
import ru.job4j.ood.lsp.foodwarehouse.Food;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private AbstractStore warehouse;
    private AbstractStore shop;
    private AbstractStore trash;
    private ControlQuality controlQuality;
    private Food cheese;
    private Food cheese2;
    private Food fish;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        controlQuality = new ControlQuality(warehouse, shop, trash);

        cheese = new Food("Cheese",
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(60),
                100,
                10);

        cheese2 = new Food("Cheese2",
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(50),
                120,
                20);

        fish = new Food("Fish",
                LocalDate.now().minusDays(20),
                LocalDate.now().minusDays(1),
                200,
                30);

        controlQuality.chosePlace(cheese);
        controlQuality.chosePlace(cheese2);
        controlQuality.chosePlace(fish);
    }

    @Test
    void whenCheeseAndCheese2GoToWarehouse() {
        assertTrue(warehouse.findAll().contains(cheese), "Cheese должен быть на складе");
        assertTrue(warehouse.findAll().contains(cheese2), "Cheese2 должен быть на складе");
        assertFalse(warehouse.findAll().contains(fish), "Fish не должен быть на складе");
    }
}
