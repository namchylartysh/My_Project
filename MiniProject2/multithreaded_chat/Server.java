package mysolution;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private ServerSocket server;
    private List<ClientHandler> clients;


    public Server(int port) throws IOException {
        server = new ServerSocket(port);
        clients = new ArrayList<>();
    }

    public void start() {
        System.out.println("Server started");
        while (true) {
            try {
                Socket clientSocket = server.accept();
                ClientHandler handler = new ClientHandler(clientSocket, this);
                handler.start();
                clients.add(handler);
            } catch (IOException e) {
                System.out.println("Error accepting client connection");
                e.printStackTrace();
            }
        }
    }

    public void broadcast(String message) {
        for (ClientHandler handler : clients) {
            handler.sendMessage(message);
        }
    }

    public void removeHandler(ClientHandler handler) {
        clients.remove(handler);
    }

    public static void main(String[] args) throws IOException {
        int port = 8000;
        Server server = new Server(port);
        server.start();
    }

    class ClientHandler extends Thread {
        private Socket client;
        private Server server;
        private BufferedReader reader;
        private PrintWriter writer;

        public ClientHandler(Socket socket, Server server) {
            this.client = socket;
            this.server = server;
        }

        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream(), true);

                String username = reader.readLine();
                server.broadcast(username + " has joined the chat");

                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    if (inputLine.equals("exit")) {
                        break;
                    }
                    server.broadcast(username + ": " + inputLine);
                }

                server.broadcast(username + " has left the chat");
                server.removeHandler(this);
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            writer.println(message);
        }
    }
}
