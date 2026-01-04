package ru.job4j.ood.lsp.foodwarehouse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodwarehouse.store.AbstractStore;
import ru.job4j.ood.lsp.foodwarehouse.store.Shop;
import ru.job4j.ood.lsp.foodwarehouse.store.Trash;
import ru.job4j.ood.lsp.foodwarehouse.store.Warehouse;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {

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
                LocalDate.now().plusDays(50),
                100,
                20);

        cheese2 = new Food("Cheese2",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(5),
                100,
                20);

        fish = new Food("Fish",
                LocalDate.now().minusDays(15),
                LocalDate.now().minusDays(1),
                100,
                20);

        controlQuality.chosePlace(cheese);
        controlQuality.chosePlace(cheese2);
        controlQuality.chosePlace(fish);
    }

    @Test
    void whenAllFoodsGoToCorrectPlace() {
        assertTrue(warehouse.findAll().contains(cheese), "Cheese should be in warehouse");
        assertTrue(shop.findAll().contains(cheese2), "Cheese2 should be in shop");
        assertTrue(trash.findAll().contains(fish), "Fish should be in trash");
    }
}
