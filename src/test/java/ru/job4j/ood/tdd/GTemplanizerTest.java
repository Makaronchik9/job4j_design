package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GTemplanizerTest {
    @Test
    public void whenReplaced() {
        GTemplanizer gen = new GTemplanizer();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Sergei");
        map.put("subject", "you");
        String rsl = gen.produce(template, map);
        String exp = "I am a Sergei, Who are you? ";
        assertEquals(rsl, exp);
    }

    @Test
    public void whenException() {
        GTemplanizer gen = new GTemplanizer();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("surname", "Sergei");
        map.put("subject", "you");
        String exp = "Template does not contain key: surname";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> gen.produce(template, map));
        String msg = exception.getMessage();
        assertTrue(msg.contains(exp));
    }
}