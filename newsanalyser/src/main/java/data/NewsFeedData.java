package data;

import java.time.LocalDateTime;

public class NewsFeedData {
    private NewsFeed newsFeed;
    private LocalDateTime localDateTime;

    public NewsFeedData(NewsFeed newsFeed, LocalDateTime localDateTime) {
        this.newsFeed = newsFeed;
        this.localDateTime = localDateTime;
    }
    public boolean newsFeedArrivedInLast10Seconds() {

        LocalDateTime timestampBefore10Seconds = LocalDateTime.now().minusSeconds(10);
        return localDateTime.isAfter(timestampBefore10Seconds);
    }

    public NewsFeed getNewsFeed() {
        return newsFeed;
    }

    public boolean newsFeedOlderThan30Seconds() {
        LocalDateTime timestampBefore30Seconds = LocalDateTime.now().minusSeconds(30);
        return localDateTime.isAfter(timestampBefore30Seconds);
    }
}
