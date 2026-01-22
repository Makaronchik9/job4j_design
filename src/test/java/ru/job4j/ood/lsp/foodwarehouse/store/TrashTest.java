package ru.job4j.ood.lsp.foodwarehouse.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodwarehouse.ControlQuality;
import ru.job4j.ood.lsp.foodwarehouse.Food;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrashTest {

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
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(20),
                100,
                20
        );

        cheese2 = new Food(
                "Cheese2",
                LocalDate.now().minusDays(20),
                LocalDate.now().minusDays(1),
                100,
                20
        );

        fish = new Food(
                "Fish",
                LocalDate.now().minusDays(15),
                LocalDate.now().plusDays(5),
                100,
                50
        );

        controlQuality.choosePlace(cheese);
        controlQuality.choosePlace(cheese2);
        controlQuality.choosePlace(fish);
    }

    @Test
    void whenExpiredFoodGoesToTrash() {
        assertTrue(trash.findAll().contains(cheese2));
        assertFalse(trash.findAll().contains(cheese));
        assertFalse(trash.findAll().contains(fish));
    }
}
