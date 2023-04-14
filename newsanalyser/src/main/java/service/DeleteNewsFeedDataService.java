package service;

import data.NewsFeedData;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeleteNewsFeedDataService implements Runnable {
    private final BlockingQueue<NewsFeedData> queue;

    public DeleteNewsFeedDataService(BlockingQueue<NewsFeedData> queue) {
        this.queue = queue;
    }

    public void startJob() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this, 60, 30, TimeUnit.SECONDS);
    }

    @Override
    public void run() {

        boolean deleted = queue.removeIf(NewsFeedData::newsFeedOlderThan30Seconds);

        if (deleted) {
            System.out.println("news feed deleted");
        }
    }
}
