import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Set<ClientHandler> clientHandlers;

    public ClientHandler(Socket socket, Set<ClientHandler> clientHandlers) {
        this.socket = socket;
        this.clientHandlers = clientHandlers;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            closeEverything();
        }
    }

    @Override
    public void run() {
        String message;
        try {
            writer.println("Welcome to the chat!");
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
                broadcast(message);
            }
        } catch (IOException e) {
            closeEverything();
        }
    }

    private void broadcast(String message) {
        for (ClientHandler client : clientHandlers) {
            if (client != this) {
                client.writer.println(message);
            }
        }
    }

    private void closeEverything() {
        try {
            clientHandlers.remove(this);
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
