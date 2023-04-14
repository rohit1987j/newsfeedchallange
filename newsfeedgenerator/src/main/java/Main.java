import socketclient.SocketClient;

public class Main {
    public static void main(String args[]) {

        int frequency = getFrequency(args);

        SocketClient socketClient = new SocketClient();
        socketClient.connect(frequency);
    }

    private static Integer getFrequency(String[] args) {
        String frequencyString = System.getProperty("frequency");

        if (frequencyString == null) {
            System.err.println("Please enter frequency of items sent per second <frequency>");
            System.exit(1);
        }

        Integer frequency = null;
        try {
            frequency = Integer.parseInt(frequencyString);
        } catch (NumberFormatException e) {
            System.err.println(String.format("frequency has to be integer, invalid frequency: %s", args[0]));
            System.exit(1);
        }

        return frequency;
    }
}
