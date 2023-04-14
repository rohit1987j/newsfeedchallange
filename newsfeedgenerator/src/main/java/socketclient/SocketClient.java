package socketclient;

import data.NewsFeed;

import java.io.IOException;
import java.io.ObjectOutputStream;

import java.net.Socket;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SocketClient {
    private Socket echoSocket = null;
    private ObjectOutputStream outputStream = null;

    public void connect(int frequency)  {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            closeOutputStream();
            closeSocket();
        }));

        connectToServer();

        initializeOutputStream();

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                new RequestSender(frequency, this),
                1,
                1,
                TimeUnit.SECONDS
        );
    }

    private void initializeOutputStream() {
        while (outputStream == null) {
            try {
                outputStream = new ObjectOutputStream(echoSocket.getOutputStream());
            } catch (IOException e) {
                System.err.println("Error while connecting to server: 127.0.0.1:8088" +  e);
            }
        }
    }

    private void connectToServer() {
        while (echoSocket == null || echoSocket.isClosed()) {
            try {
                echoSocket = new Socket("127.0.0.1", 8088);
            } catch (IOException e) {
                System.err.println("Error while connecting to server: 127.0.0.1:8088" +  e);
            }
        }
    }

    private void closeOutputStream() {
        System.err.println("closing outputstream");
        if (outputStream != null) {
            try {
                outputStream.close();
                outputStream = null;
            } catch (IOException failed) {
                //failed
            }
        }
    }

    private void closeSocket() {
        System.err.println("closing socket");
        if (echoSocket != null) {
            try {
                echoSocket.close();
                echoSocket = null;
                outputStream = null;
            } catch (IOException failed) {
                //failed
            }
        }
    }

    public void writeNewsFeed(NewsFeed newsFeed) {
        try {

            outputStream.writeObject(newsFeed);
            System.out.println("newsfeed send to server");

        } catch (IOException e) {
            System.err.println("failed to send data to server." + e);
            reconnect();
        }
    }

    private void reconnect() {
        closeSocket();
        connectToServer();
        initializeOutputStream();
    }
}
