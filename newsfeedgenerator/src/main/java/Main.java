import socketclient.SocketClient;

public class Main {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("Please enter frequency of items sent per second <frequency>");
            System.exit(1);
        }
        Integer frequency = null;
        try {
            frequency = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println(String.format("frequency has to be integer, invalid frequency: %s", args[0]));
            System.exit(1);
        }

        SocketClient socketClient = new SocketClient();
        socketClient.connect(frequency);
    }
}
