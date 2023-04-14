package server;

import data.NewsFeed;
import data.NewsFeedData;

import java.net.*;
import java.io.*;

import java.time.LocalDateTime;

import java.util.concurrent.BlockingQueue;

public class ServerThread extends Thread {
    private Socket socket;
    private BlockingQueue<NewsFeedData> queue;

    public ServerThread(Socket socket, BlockingQueue<NewsFeedData> queue) {
        super("ServerThread");
        this.socket = socket;
        this.queue = queue;
    }

    public void run() {

        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {

            NewsFeed newsFeed;

            while ((newsFeed = (NewsFeed) objectInputStream.readObject()) != null) {
                if (newsFeed.isPositive()) {

                    try {
                        queue.put(new NewsFeedData(newsFeed, LocalDateTime.now()));

                    } catch (InterruptedException e) {
                        System.err.println("Error while reading " + e);
                    }
                }
            }
            socket.close();
        } catch (EOFException toBeIgnored) {
            //TO BE IGNORED BLOCK AS WE ARE POLLING ON EMPTY STREAM
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error while reading " + e);
        }
    }
}
