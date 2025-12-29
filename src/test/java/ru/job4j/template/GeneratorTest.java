package ru.job4j.template;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class GeneratorTest {

    private final Generator generator = new Generator() {
        @Override
        public String produce(String template, Map<String, String> args) {
            throw new UnsupportedOperationException("Только для теста интерфейса");
        }
    };

    @Test
    void whenAllKeysPresentThenProduceCorrectString() {
        // Arrange
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        );

        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void whenTemplateHasMissingKeyThenThrowException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev"
        );

        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenArgsHasExtraKeyThenThrowException() {
        String template = "I am a ${name}";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you" // лишний ключ
        );

        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTemplateAndArgsAreEmptyThenReturnEmptyString() {
        String template = "";
        Map<String, String> args = Map.of();

        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
