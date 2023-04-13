package service;

import data.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NewsAnalyserServiceUnitTest {

    private final ResultPrintService resultPrintService = mock(ResultPrintService.class);

    private NewsAnalyserService newsAnalyserService;

    private final ArgumentCaptor<Result> resultArgumentCaptor = ArgumentCaptor.forClass(Result.class);


    @Test
    public void resultIsCorrectWhenDuplicateHeadlinesArePresent() {
        BlockingQueue<NewsFeedData> queue = new LinkedBlockingQueue<>();
        queue.add(createNewsFeedData(Collections.singletonList(HeadlineWord.über), Priority.NINE));
        queue.add(createNewsFeedData(Arrays.asList(HeadlineWord.über), Priority.EIGHT));
        queue.add(createNewsFeedData(Arrays.asList(HeadlineWord.über), Priority.SEVEN));

        newsAnalyserService = new NewsAnalyserService(queue, resultPrintService);

        newsAnalyserService.run();

        verify(resultPrintService).print(resultArgumentCaptor.capture());

        Result result = resultArgumentCaptor.getValue();

        assertEquals(3l, result.getCount());
        assertEquals(1l, result.getNewsFeedList().stream().count());
        assertEquals(Priority.NINE, result.getNewsFeedList().get(0).getPriority());
        assertEquals(Arrays.asList(HeadlineWord.über), result.getNewsFeedList().get(0).getHeadline());
    }

    @Test
    public void resultIsCorrectWhenHeadlinesWithDifferentPriorityArePresent() {
        BlockingQueue<NewsFeedData> queue = new LinkedBlockingQueue<>();
        queue.add(createNewsFeedData(Arrays.asList(HeadlineWord.über, HeadlineWord.up), Priority.NINE));
        queue.add(createNewsFeedData(Arrays.asList(HeadlineWord.über, HeadlineWord.down), Priority.EIGHT));
        queue.add(createNewsFeedData(Arrays.asList(HeadlineWord.über, HeadlineWord.failure), Priority.SEVEN));
        queue.add(createNewsFeedData(Arrays.asList(HeadlineWord.über, HeadlineWord.down), Priority.SIX));
        queue.add(createNewsFeedData(Arrays.asList(HeadlineWord.über, HeadlineWord.failure), Priority.FIVE));
        queue.add(createNewsFeedData(Arrays.asList(HeadlineWord.über, HeadlineWord.up), Priority.FOUR));

        newsAnalyserService = new NewsAnalyserService(queue, resultPrintService);

        newsAnalyserService.run();

        verify(resultPrintService).print(resultArgumentCaptor.capture());

        Result result = resultArgumentCaptor.getValue();

        assertEquals(6l, result.getCount());
        assertEquals(3l, result.getNewsFeedList().stream().count());
        assertEquals(result.getNewsFeedList().stream().map(NewsFeed::getPriority).collect(Collectors.toList()), Arrays.asList(Priority.NINE, Priority.EIGHT, Priority.SEVEN));
    }

    public NewsFeedData createNewsFeedData(List<HeadlineWord> words, Priority priority) {
        NewsFeed newsFeed = new NewsFeed(words, priority);
        return new NewsFeedData(newsFeed, LocalDateTime.now());
    }
}
