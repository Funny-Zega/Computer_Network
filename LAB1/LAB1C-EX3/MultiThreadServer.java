import java.io.*;
import java.net.*;
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server is Running");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New Client is connected!");
            new ClientHandler(clientSocket).start();
        }
    }
}
    class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Client: " + msg);
                out.println("Server received: " + msg);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}