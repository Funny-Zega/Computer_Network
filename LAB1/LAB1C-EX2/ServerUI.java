import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class ServerUI {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ServerUI::new);
    }
    public ServerUI() {
        JFrame frame = new JFrame("Chat Server");
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
    // Start server
        new Thread(() -> {
        try {
            serverSocket = new ServerSocket(12345);
            chatArea.append("Server started. Waiting for client...\n");
            socket = serverSocket.accept();
            chatArea.append("Client connected.\n");
            
            in = new BufferedReader(new
        InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String msg;
            while ((msg = in.readLine()) != null) {
                chatArea.append("Client: " + msg + "\n");
            }
        } catch (IOException e) {
                chatArea.append("Connection closed.\n");
            }
        }).start();
    // Send message

    ActionListener sendAction = e -> {
    String text = inputField.getText().trim();
    if (!text.isEmpty() && out != null) {
        out.println(text);
        chatArea.append("You: " + text + "\n");
        inputField.setText("");
    }
    };
        sendButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);
    }
}