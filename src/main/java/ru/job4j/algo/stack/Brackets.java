package ru.job4j.algo.stack;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Map;

public class Brackets {

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> pairs = Map.of(
                ')', '(',
                ']', '[',
                '}', '{'
        );

        for (char ch : s.toCharArray()) {
            if (pairs.containsValue(ch)) {
                stack.push(ch);
            } else if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}