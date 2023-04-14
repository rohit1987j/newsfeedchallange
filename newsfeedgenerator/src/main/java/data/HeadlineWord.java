package data;

import java.util.Arrays;

public enum HeadlineWord {
    up(1, true),
    down(2, false),
    rise(3,true),
    fall(4, false),
    good(5,true),
    bad(6, false),
    success(7,true),
    failure(8, false),
    high(9,true),
    low(10, false),
    über(11,true),
    unter(12, false);

    private int index;

    private boolean isPositive;

    public int getIndex()  {
        return index;
    }

    HeadlineWord(int index, boolean isPositive) {
        this.index = index;
        this.isPositive = isPositive;
    }

    public static int getMinIndex() {
        return Arrays.stream(HeadlineWord.values()).map(HeadlineWord::getIndex).min(Integer::compareTo).get();
    }

    public static int getMaxIndex() {
        return Arrays.stream(HeadlineWord.values()).map(HeadlineWord::getIndex).max(Integer::compareTo).get();
    }

    public boolean isPositive() {
        return isPositive;
    }

    public boolean match(int index) {
        return this.index == index;
    }
}
