package ru.job4j.ood.dip;

public class DipExample3 {

    // Нарушение DIP
    // класс напрямую зависит от конкретного магазина GroceryShop,
    // отсутствует абстракция поставщика
    private GroceryShop groceryShop;

    public DipExample3() {
        this.groceryShop = new GroceryShop();
    }

    public void sendProduct() {
    }

    public class GroceryShop {
    }
}
