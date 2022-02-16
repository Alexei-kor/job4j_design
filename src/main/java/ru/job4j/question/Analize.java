package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int chaged = 0;
        int deleted = 0;
        Map<Integer, String> mapPrevious = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        Iterator<User> iterator = current.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (!previous.contains(user)) {
                if (mapPrevious.get(user.getId()) == null) {
                    added++;
                } else {
                    chaged++;
                }
            }
        }
        deleted = previous.size() + added - current.size();
        return new Info(added, chaged, deleted);
    }

}
