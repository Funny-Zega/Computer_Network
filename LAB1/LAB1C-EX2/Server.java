import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server started. Waiting for client...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        
        new Thread(() -> {
            String msg;
            try {
                while ((msg = in.readLine()) != null) {
                System.out.println("Client: " + msg);
            }
        } catch (IOException e) { }
        }).start();

            String input;
                while ((input = console.readLine()) != null) {
                    out.println(input);
            }
            socket.close();
            serverSocket.close();
        }
    }