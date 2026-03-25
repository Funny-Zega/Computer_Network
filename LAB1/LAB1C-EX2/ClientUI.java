import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class ClientUI {
private Socket socket;
private BufferedReader in;
private PrintWriter out;

public static void main(String[] args) {
    SwingUtilities.invokeLater(ClientUI::new);
}

public ClientUI() {
    JFrame frame = new JFrame("Chat Client");
    JTextArea chatArea = new JTextArea();
    chatArea.setEditable(false);
    JTextField inputField = new JTextField();
    JButton sendButton = new JButton("Send");
    frame.setLayout(new BorderLayout());
    frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
    JPanel bottomPanel = new JPanel(new BorderLayout());
    bottomPanel.add(inputField, BorderLayout.CENTER);
    bottomPanel.add(sendButton, BorderLayout.EAST);
    frame.add(bottomPanel, BorderLayout.SOUTH);
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

// Connect to server
    try {
        socket = new Socket("localhost", 12345);
        chatArea.append("Connected to server.\n");
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

// Thread to listen for messages
        new Thread(() -> {
        String msg;
        try {
            while ((msg = in.readLine()) != null) {
                chatArea.append("Server: " + msg + "\n");
                }
        } catch (IOException e) {
                chatArea.append("Connection closed.\n");
        }
        }).start();
    } catch (IOException e) {
            chatArea.append("Failed to connect to server.\n");
            return;
    }
// Send message when button is clicked or Enter pressed
    ActionListener sendAction = e -> {
    String text = inputField.getText().trim();
    if (!text.isEmpty()) {
        out.println(text);
        chatArea.append("You: " + text + "\n");
        inputField.setText("");
    }
    };
    sendButton.addActionListener(sendAction);
    inputField.addActionListener(sendAction);
    }
}