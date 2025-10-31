package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");

        assertThat(array)
                .isNotEmpty()
                .hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .startsWith("first")
                .endsWith("four", "five")
                .containsSequence("second", "three");
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("alpha", "beta", "gamma", "delta");

        assertThat(list)
                .isNotNull()
                .hasSize(4)
                .contains("gamma", "beta")
                .containsExactly("alpha", "beta", "gamma", "delta")
                .containsExactlyInAnyOrder("beta", "alpha", "delta", "gamma")
                .doesNotContain("omega")
                .startsWith("alpha")
                .endsWith("gamma", "delta")
                .element(2).isEqualTo("gamma");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("one", "two", "three", "two");

        assertThat(set)
                .isNotEmpty()
                .hasSize(3) // "two" дублируется, в Set хранится один раз
                .contains("one", "two")
                .doesNotContain("zero")
                .allSatisfy(e -> assertThat(e.length()).isGreaterThan(2))
                .anySatisfy(e -> assertThat(e).isEqualTo("two"));
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("key1", "key2", "key3");

        assertThat(map)
                .isNotEmpty()
                .hasSize(3)
                .containsKeys("key1", "key2", "key3")
                .containsValues(0, 1, 2)
                .doesNotContainKey("key0")
                .doesNotContainValue(99)
                .containsEntry("key2", 1)
                .allSatisfy((k, v) -> {
                    assertThat(k).startsWith("key");
                    assertThat(v).isLessThan(5);
                });
    }
}
