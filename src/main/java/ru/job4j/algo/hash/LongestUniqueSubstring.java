package ru.job4j.algo.hash;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {

    public static String longestUniqueSubstring(String str) {
        if (str.isEmpty()) {
            return "";
        }

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLen = 0;
        int start = 0;

        for (int right = 0; right < str.length(); right++) {
            char ch = str.charAt(right);

            if (map.containsKey(ch) && map.get(ch) >= left) {
                left = map.get(ch) + 1;
            }

            map.put(ch, right);

            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                start = left;
            }
        }

        return str.substring(start, start + maxLen);
    }
}
