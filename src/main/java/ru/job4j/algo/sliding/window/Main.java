package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return new int[]{-1, -1};
        }

        intervals.sort(Comparator.comparingInt(i -> i.start));

        PriorityQueue<Interval> active = new PriorityQueue<>(Comparator.comparingInt(i -> i.end));

        int maxOverlap = 0;
        int maxStart = -1;
        int maxEnd = -1;

        for (Interval interval : intervals) {
            while (!active.isEmpty() && active.peek().end <= interval.start) {
                active.poll();
            }

            active.add(interval);

            if (active.size() > maxOverlap) {
                maxOverlap = active.size();

                int left = active.stream().mapToInt(i -> i.start).max().getAsInt();
                int right = active.stream().mapToInt(i -> i.end).min().getAsInt();

                maxStart = left;
                maxEnd = right;
            }
        }

        return new int[]{maxStart, maxEnd};
    }
}
