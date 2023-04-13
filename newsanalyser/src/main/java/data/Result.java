package data;

import java.util.List;

public class Result {
    private final long count;

    private final List<NewsFeed> newsFeedList;

    public Result(long count, List<NewsFeed> newsFeedList) {
        this.count = count;
        this.newsFeedList = newsFeedList;
    }

    public long getCount() {
        return count;
    }

    public List<NewsFeed> getNewsFeedList() {
        return newsFeedList;
    }
}
