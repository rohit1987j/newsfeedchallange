package socketclient;

import data.NewsFeed;

import service.NewsFeedGenerator;

import java.io.IOException;
import java.io.ObjectOutputStream;

import java.net.Socket;

import java.util.ArrayList;
import java.util.List;

public class RequestSender implements Runnable {
    private final int frequency;

    public RequestSender(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public void run() {

        try (
                Socket echoSocket = new Socket("127.0.0.1", 8088);
                ObjectOutputStream outputStream = new ObjectOutputStream(echoSocket.getOutputStream());
        ) {


            for (int counter = 0; counter < frequency; counter++) {

                outputStream.writeObject(new NewsFeedGenerator().generate());

            }
            System.out.println("newsfeed send to server");

        } catch (IOException e) {
            System.err.println("Error while writing to server" +  e);
        }
    }
}