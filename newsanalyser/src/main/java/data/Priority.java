package data;

import java.util.Arrays;

public enum Priority {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9);

    private int value;

    Priority(int value) {
        this.value = value;
    }

    public static Priority from(int value) {
        return Arrays.stream(Priority.values()).filter(priority -> priority.value == value)
                                               .findFirst()
                                               .get();
    }

    public int getValue() {
        return value;
    }
}
