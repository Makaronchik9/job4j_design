package ru.job4j.ood.lsp.foodwarehouse.store;

import ru.job4j.ood.lsp.foodwarehouse.ControlQuality;
import ru.job4j.ood.lsp.foodwarehouse.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
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

        cheese = new Food("Cheese", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 100, 20);
        cheese2 = new Food("Cheese2", LocalDate.now().minusDays(20), LocalDate.now().plusDays(2), 100, 20);
        fish = new Food("Fish", LocalDate.now().minusDays(10), LocalDate.now().minusDays(1), 100, 20);

        controlQuality.chosePlace(cheese);
        controlQuality.chosePlace(cheese2);
        controlQuality.chosePlace(fish);
    }

    @Test
    void whenAllCheeseIsGoingToShop() {
        assertTrue(shop.findAll().contains(cheese));
        assertTrue(shop.findAll().contains(cheese2));
        assertFalse(shop.findAll().contains(fish));
    }

    @Test
    void whenPriceOfCheeseWithoutDiscount() {
        assertEquals(100, cheese.getPrice());
    }

    @Test
    void whenPriceOfCheese2WithDiscount() {
        assertEquals(80, cheese2.getPrice());
    }
}
