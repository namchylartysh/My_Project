package multithreaded_chat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatServer {

    public static int SERVER_PORT = 7077;
    public static String SERVER_IP = "localhost";
    public static List<String> users = new ArrayList<>();

    public static List<ClientHandler> clients;

    public static void main(String[] args) {
        new ChatServer();
    }

    public ChatServer() {
        clients = new ArrayList<>();
        System.out.println("Server started...");
        try (ServerSocket server = new ServerSocket(SERVER_PORT)) {
            while (true) {
                Socket socket = server.accept();
                //String name = clients.getUserName();
                ClientHandler client = new ClientHandler(socket);
                clients.add(client);
                new Thread(client).start();
                System.out.println(client.name + ": joined.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class ClientHandler implements Runnable {
        private BufferedReader reader;
        private PrintWriter writer;
        private Socket client;
        private String name;
        private Scanner scanner;

        public ClientHandler(Socket client) {
            this.client = client;
            this.scanner = new Scanner(System.in);
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream());
                name = reader.readLine();
                users.add(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendToAll(String user, String message) {
            for (ClientHandler client : clients) {
                if (!client.getUserName().equals(user)) {
                    client.send(user, message);
                }
            }
        }

        public void send(String uname, String message) {
            writer.println(uname + ": " + message);
            writer.flush();
        }

        public String getUserName() {
            return name;
        }

        @Override
        public void run() {
            String message;
            try {
                do {
                    message = reader.readLine();
                    sendToAll(name, message);
                } while (!message.equals("end"));
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clients.remove(this);
            System.out.println(name + ": disconnected");
        }
    }

}
