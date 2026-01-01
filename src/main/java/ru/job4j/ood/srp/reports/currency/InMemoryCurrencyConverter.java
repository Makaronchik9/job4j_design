package ru.job4j.ood.srp.reports.currency;

public class InMemoryCurrencyConverter implements CurrencyConverter {

    @Override
    public double convert(Currency source, double sourceValue, Currency target) {
        if (source == Currency.RUB && target == Currency.USD) {
            return sourceValue * 0.0162;
        }
        return sourceValue;
    }
}
