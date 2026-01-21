package ru.job4j.ood.lsp.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);

        assertThat(new Menu.MenuItemInfo(
                "Сходить в магазин",
                List.of("Купить продукты"),
                STUB_ACTION,
                "2."))
                .isEqualTo(menu.select("Сходить в магазин").get());

        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"),
                STUB_ACTION,
                "2.1."))
                .isEqualTo(menu.select("Купить продукты").get());

        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку",
                List.of(),
                STUB_ACTION,
                "1."))
                .isEqualTo(menu.select("Покормить собаку").get());
    }

    @Test
    public void whenSelectExistingItemThenReturnItemInfo() {
        SimpleMenu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Task 1", () -> {});
        menu.add("Task 1", "Task 1.1", () -> {});

        Optional<Menu.MenuItemInfo> itemInfo = menu.select("Task 1.1");

        Assertions.assertTrue(itemInfo.isPresent());
        Assertions.assertEquals("Task 1.1", itemInfo.get().getName());
    }
}
