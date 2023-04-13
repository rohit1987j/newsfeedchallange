package socketclient;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SocketClient {
    public void connect(int frequency)  {

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                new RequestSender(frequency),
                1,
                1,
                TimeUnit.SECONDS
        );
    }
}
