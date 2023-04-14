package service;

import data.Priority;

import java.util.Arrays;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class PriorityGenerator {

    public static Priority getPriority() {
        int[] priorities = Arrays.stream(Priority.values())
                .sorted(Comparator.reverseOrder())
                .mapToInt(Priority::getValue)
                .toArray();

        int totalPriority = 0;
        for (int i = 0; i < priorities.length; i++) {
            totalPriority += priorities[i];
        }

        int randomValue = ThreadLocalRandom.current().nextInt(totalPriority) + 1;

        int cumulativePriority = 0;

        for (int i = 0; i < priorities.length; i++) {
            cumulativePriority += priorities[i];
            if (randomValue <= cumulativePriority) {
                return Priority.from(priorities[i]);
            }
        }

        return Priority.from(priorities[priorities.length - 1]);
    }
}
