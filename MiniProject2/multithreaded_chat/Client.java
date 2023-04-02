package multithreaded_chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client extends JFrame implements ActionListener {
    private static String hostname;
    private JTextField msgField;
    private JTextArea dialogue;
    private JButton send;
    private JButton exit;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;



    public static void main(String[] args) throws IOException {

        new Client(hostname, 8000);
    }

    public Client(String hostname, int port) throws IOException {
        super("Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        msgField = new JTextField(40);
        dialogue = new JTextArea(10, 40);
        dialogue.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(dialogue);

        send = new JButton("Send");
        send.addActionListener(this);

        exit = new JButton("Exit");
        exit.addActionListener(this);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(msgField, BorderLayout.CENTER);
        panel.add(send, BorderLayout.EAST);
        panel.add(exit, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        pack();
        setVisible(true);

        socket = new Socket(hostname, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);

        String username = JOptionPane.showInputDialog(this, "Enter username:");
        writer.println(username);

        String message;
        while ((message = reader.readLine()) != null) {
            dialogue.append(message + "\n");
        }
        socket.close();
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send || e.getSource() == msgField) {
            String message = msgField.getText();
            writer.println(message);
            msgField.setText("");
        } else if (e.getSource() == exit) {
            writer.println("exit");
            System.exit(0);
        }
    }
}
