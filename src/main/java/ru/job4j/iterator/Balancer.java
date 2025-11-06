package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }
        int index = 0;
        int size = nodes.size();
        while (source != null && source.hasNext()) {
            Integer value = source.next();
            ArrayList<Integer> target = nodes.get(index % size);
            target.add(value);
            index++;
        }
    }
}
