package multithreaded_chat;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static multithreaded_chat.ChatServer.SERVER_IP;
import static multithreaded_chat.ChatServer.SERVER_PORT;

public class ChatClient extends JFrame implements ActionListener {

    PrintWriter writer;
    BufferedReader reader;
    JTextField mgsField;
    JTextArea dialogue;
    JButton exit;
    JButton send;
    Socket client;


    public static void main(String[] args) throws IOException {
        String name = JOptionPane.showInputDialog(null, "Enter your name", "Username", JOptionPane.PLAIN_MESSAGE);
        String servername = SERVER_IP;
        new ChatClient(name, servername);
    }

    public ChatClient(String uname, String servername) {
        super(uname);
        try {
            client = new Socket(servername, SERVER_PORT);
            writer = new PrintWriter(client.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Connection to server...");
            writer.println(uname);
            buildInterface();
            new Thread(new CommandHandler(writer)).start();
            String message;
            while (true) {
                message = reader.readLine();
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connection closed.");
    }

    private void buildInterface() {
        setTitle("Client for network chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (writer != null) {
                    writer.println("exit");
                    writer.close();
                }
            }
        });
        dialogue = new JTextArea();
        dialogue.setRows(10);
        dialogue.setColumns(50);
        dialogue.setLineWrap(true);
        dialogue.setEditable(true);
        JScrollPane sp = new JScrollPane(dialogue, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(sp, "Center");
        send = new JButton("Send");
        exit = new JButton("Exit");
        mgsField = new JTextField(50);
        JPanel panel = new JPanel( new FlowLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(mgsField);
        panel.add(send);
        panel.add(exit);
        add(panel, "South");
        send.addActionListener(this);
        exit.addActionListener(this);
        setVisible(true);
        pack();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            writer.println("end");
            System.exit(0);
        } else {
            writer.println(mgsField.getText());
        }
    }

    private class CommandHandler implements Runnable {
        private PrintWriter writer;
        private Scanner scanner;

        public CommandHandler(PrintWriter writer) {
            this.writer = writer;
            this.scanner = new Scanner(System.in);
        }

        @Override
        public void run() {
            String message;
            while(true) {
                message = scanner.nextLine();
                writer.println(message);
                writer.flush();
            }
        }
    }
}
