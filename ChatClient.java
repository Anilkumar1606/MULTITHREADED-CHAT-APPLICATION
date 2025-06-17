import java.io.*;
import java.net.*;

public class ChatClient {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ChatClient(String address, int port) {
        try {
            socket = new Socket(address, port);
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintWriter(socket.getOutputStream(), true);

            new Thread(new ReceiveMessages()).start();

            String message;
            while ((message = reader.readLine()) != null) {
                writer.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ReceiveMessages implements Runnable {
        public void run() {
            try (BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = socketReader.readLine()) != null) {
                    System.out.println("Server: " + message);
                }
            } catch (IOException e) {
                System.out.println("Disconnected from server.");
            }
        }
    }

    public static void main(String[] args) {
        new ChatClient("localhost", 12345);
    }
}
