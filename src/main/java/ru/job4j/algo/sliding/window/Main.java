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

        intervals = new ArrayList<>(intervals);
        intervals.sort(Comparator.comparingInt(i -> i.start));

        PriorityQueue<Interval> activeIntervals = new PriorityQueue<>(Comparator.comparingInt(i -> i.end));

        int maxOverlap = 0;
        int maxStart = -1;
        int maxEnd = -1;

        for (Interval interval : intervals) {
            while (!activeIntervals.isEmpty() && activeIntervals.peek().end < interval.start) {
                activeIntervals.poll();
            }

            activeIntervals.add(interval);

            if (activeIntervals.size() > maxOverlap) {
                maxOverlap = activeIntervals.size();
                maxStart = interval.start;
                maxEnd = activeIntervals.peek().end;
            }
        }

        return new int[]{maxStart, maxEnd};
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>(List.of(
                new Interval(1, 4),
                new Interval(2, 6),
                new Interval(3, 5),
                new Interval(7, 8)
        ));

        int[] result = findMaxOverlapInterval(intervals);
        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}
