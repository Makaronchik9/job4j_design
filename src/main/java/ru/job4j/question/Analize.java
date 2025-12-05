package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {

        Map<Integer, String> mapPrevious = new HashMap<>();
        for (User u : previous) {
            mapPrevious.put(u.getId(), u.getName());
        }

        int added = 0;
        int changed = 0;

        for (User u : current) {
            String oldName = mapPrevious.remove(u.getId());

            if (oldName == null) {
                added++;
            } else if (!oldName.equals(u.getName())) {
                changed++;
            }
        }

        int deleted = mapPrevious.size();

        return new Info(added, changed, deleted);
    }
}
