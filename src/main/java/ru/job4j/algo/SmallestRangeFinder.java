package ru.job4j.algo;

import java.util.HashMap;
import java.util.Map;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        if (nums == null || nums.length < k) return null;

        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int[] result = null;

        for (int right = 0; right < nums.length; right++) {
            countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);

            while (countMap.size() >= k) {
                if (right - left < minLen) {
                    minLen = right - left;
                    result = new int[]{left, right};
                }
                int leftNum = nums[left];
                countMap.put(leftNum, countMap.get(leftNum) - 1);
                if (countMap.get(leftNum) == 0) {
                    countMap.remove(leftNum);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 4, 5};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: [" + result[0] + ", " + result[1] + "]");
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
