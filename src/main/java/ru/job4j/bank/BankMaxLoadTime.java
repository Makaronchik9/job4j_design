package ru.job4j.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List<int[]> visitTimes) {
        List<Event> events = new ArrayList<>();
        for (int[] visit : visitTimes) {
            events.add(new Event(visit[0], EventType.ARRIVAL));
            events.add(new Event(visit[1], EventType.DEPARTURE));
        }

        Collections.sort(events);

        int currentLoad = 0;
        int maxLoad = 0;
        int maxLoadStartTime = 0;
        int maxLoadEndTime = 0;

        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (event.type == EventType.ARRIVAL) {
                currentLoad++;
                if (currentLoad > maxLoad) {
                    maxLoad = currentLoad;
                    maxLoadStartTime = event.time;
                    if (i + 1 < events.size()) {
                        maxLoadEndTime = events.get(i + 1).time;
                    } else {
                        maxLoadEndTime = event.time;
                    }
                }
            } else {
                currentLoad--;
            }
        }

        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return this.type == EventType.ARRIVAL ? -1 : 1;
            }
            return Integer.compare(this.time, other.time);
        }
    }

    enum EventType {
        ARRIVAL,
        DEPARTURE
    }
}
