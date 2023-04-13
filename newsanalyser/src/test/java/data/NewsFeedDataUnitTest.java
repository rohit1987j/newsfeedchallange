package data;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class NewsFeedDataUnitTest {

    @Test
    public void testNewsFeedArrivedInLast10SecondsWorksAsExpected() {
        LocalDateTime now = LocalDateTime.now();
        NewsFeedData newsFeedData = new NewsFeedData(new NewsFeed(Arrays.asList(HeadlineWord.rise), Priority.FIVE), now);

        assertEquals(true, newsFeedData.newsFeedArrivedInLast10Seconds());

        newsFeedData = new NewsFeedData(new NewsFeed(Arrays.asList(HeadlineWord.rise), Priority.FIVE), now.minusSeconds(10));

        assertEquals(false, newsFeedData.newsFeedArrivedInLast10Seconds());
    }
}
