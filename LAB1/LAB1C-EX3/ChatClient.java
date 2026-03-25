import java.io.*;
import java.net.*;
public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 12345);
        System.out.println("Connecting Successfully");
        BufferedReader in = new BufferedReader(
        
        new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String msg;
        while (true) {
            msg = stdin.readLine();
            out.println(msg);
            String response = in.readLine();
            System.out.println("Server: " + response);
        }
    }
}

