package service;

import data.NewsFeed;
import data.NewsFeedData;
import data.Result;

import java.util.List;

import java.util.Map;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import java.util.function.Function;
import java.util.function.Predicate;

import java.util.stream.Collectors;

public class NewsAnalyserService implements Runnable {
    private static final int MAX_SIZE = 3;
    private final BlockingQueue<NewsFeedData> queue;
    private final ResultPrintService service;

    public NewsAnalyserService(BlockingQueue<NewsFeedData> queue, ResultPrintService resultPrintService) {
        this.queue = queue;
        this.service = resultPrintService;
    }

    public void startAnalysis() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this, 10, 10, TimeUnit.SECONDS);
    }

    @Override
    public void run() {

        List<NewsFeed> newsFeedInLast10Seconds = queue.stream().filter(NewsFeedData::newsFeedArrivedInLast10Seconds)
                                                               .map(NewsFeedData::getNewsFeed)
                                                               .collect(Collectors.toList());

        long totalCount = newsFeedInLast10Seconds.size();

        List<NewsFeed> result = newsFeedInLast10Seconds.stream()
                .sorted((nf1, nf2) -> Integer.compare(nf2.getPriority().getValue(), nf1.getPriority().getValue()))
                .filter(distinctByKey(NewsFeed::getHeadlineAsString))
                .limit(MAX_SIZE)
                .collect(Collectors.toList());

        service.print(new Result(totalCount, result));
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
