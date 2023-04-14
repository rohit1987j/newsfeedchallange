package server;

import data.NewsFeedData;
import service.DeleteNewsFeedDataService;
import service.NewsAnalyserService;
import service.ResultPrintService;

import java.net.*;
import java.io.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private final BlockingQueue<NewsFeedData> blockingQueue = new LinkedBlockingQueue<>();
    public void start(int port) {

        boolean listening = true;

        NewsAnalyserService newsAnalyserService = new NewsAnalyserService(blockingQueue, new ResultPrintService());
        newsAnalyserService.startAnalysis();

        DeleteNewsFeedDataService deleteNewsFeedService = new DeleteNewsFeedDataService(blockingQueue);
        deleteNewsFeedService.startJob();

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server started on port " + port);

            while (listening) {
                new ServerThread(serverSocket.accept(), blockingQueue).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
}