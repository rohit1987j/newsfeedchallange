package data;

import java.io.Serializable;

import java.util.List;

import java.util.stream.Collectors;

public class NewsFeed implements Serializable {

    private static final int MINIMUM_CRITERIA_PERCENTAGE = 50;
    private List<HeadlineWord> headline;
    private Priority priority;

    public NewsFeed(List<HeadlineWord> headline, Priority priority) {
        this.headline = headline;
        this.priority = priority;
    }

    public List<HeadlineWord> getHeadline() {
        return headline;
    }

    public String getHeadlineAsString() {
        return headline.stream().map(HeadlineWord::name).collect(Collectors.joining(" "));
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isPositive() {

        long totalCount = headline.stream().count();
        long positiveCount = headline.stream().filter(HeadlineWord::isPositive).count();

        int percentage = (int) (positiveCount * 100 / totalCount);
        return percentage > MINIMUM_CRITERIA_PERCENTAGE;
    }
}
