package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {

    @Test
    void whenNumberIsThreeThenFizz() {
        assertThat(FizzBuzz.value(3)).isEqualTo("Fizz");
    }

    @Test
    void whenNumberIsFiveThenBuzz() {
        assertThat(FizzBuzz.value(5)).isEqualTo("Buzz");
    }

    @Test
    void whenNumberIsFifteenThenFizzBuzz() {
        assertThat(FizzBuzz.value(15)).isEqualTo("FizzBuzz");
    }

    @Test
    void whenNumberIsNotMultipleThenNumber() {
        assertThat(FizzBuzz.value(7)).isEqualTo("7");
    }
}
