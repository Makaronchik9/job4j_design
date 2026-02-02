package ru.job4j.ood.dip;

public class DipExample2 {

    // Нарушение DIP
    // зависимость от конкретного способа оплаты CreditCard,
    // невозможно подменить реализацию без изменения класса
    private CreditCard creditCard;

    public DipExample2() {
        this.creditCard = new CreditCard();
    }

    public void payment() {
    }

    public class CreditCard {
    }
}
