package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FoolTest {

    @Test
    void whenNumberNotDivisibleBy3And5ThenReturnNumber() {
        String result = Fool.ret(1);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void whenNumberDivisibleBy3ThenFizz() {
        String result = Fool.ret(3);
        assertThat(result).isEqualTo("Fizz");
    }

    @Test
    void whenNumberDivisibleBy5ThenBuzz() {
        String result = Fool.ret(5);
        assertThat(result).isEqualTo("Buzz");
    }

    @Test
    void whenNumberDivisibleBy3And5ThenFizzBuzz() {
        String result = Fool.ret(15);
        assertThat(result).isEqualTo("FizzBuzz");
    }

    @Test
    void whenNumberIs30ThenFizzBuzz() {
        String result = Fool.ret(30);
        assertThat(result).isEqualTo("FizzBuzz");
    }
}
