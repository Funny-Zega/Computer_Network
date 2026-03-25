import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connected to server.");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        new Thread(() -> {
        String msg;
    try {
        while ((msg = in.readLine()) != null) {
            System.out.println("Server: " + msg);
        }
    } catch (IOException e) { }
    }).start();
    String input;
        while ((input = console.readLine()) != null) {
            out.println(input);
        }
        socket.close();
    }
}