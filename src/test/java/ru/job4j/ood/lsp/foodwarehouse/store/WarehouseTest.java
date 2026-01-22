package ru.job4j.ood.lsp.foodwarehouse.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodwarehouse.ControlQuality;
import ru.job4j.ood.lsp.foodwarehouse.Food;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private Warehouse warehouse;
    private Shop shop;
    private Trash trash;
    private ControlQuality controlQuality;

    private Food cheese;
    private Food cheese2;
    private Food fish;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();

        controlQuality = new ControlQuality(List.of(warehouse, shop, trash));

        cheese = new Food(
                "Cheese",
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(60),
                100,
                10
        );

        cheese2 = new Food(
                "Cheese2",
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(50),
                120,
                20
        );

        fish = new Food(
                "Fish",
                LocalDate.now().minusDays(20),
                LocalDate.now().minusDays(1),
                200,
                30
        );

        controlQuality.choosePlace(cheese);
        controlQuality.choosePlace(cheese2);
        controlQuality.choosePlace(fish);
    }

    @Test
    void whenCheeseAndCheese2GoToWarehouse() {
        assertTrue(warehouse.findAll().contains(cheese));
        assertTrue(warehouse.findAll().contains(cheese2));
        assertFalse(warehouse.findAll().contains(fish));
    }
}
