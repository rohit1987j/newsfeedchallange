package socketclient;

import service.NewsFeedGenerator;

public class RequestSender implements Runnable {
    private final int frequency;
    private final SocketClient socketClient;

    public RequestSender(int frequency, SocketClient socketClient) {
        this.frequency = frequency;
        this.socketClient = socketClient;
    }

    @Override
    public void run() {

            for (int counter = 0; counter < frequency; counter++) {

                socketClient.writeNewsFeed(new NewsFeedGenerator().generate());

            }
    }
}