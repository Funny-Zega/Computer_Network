import java.io.*;
import java.net.*;
public class DownloadGoogle {
public static void main(String[] args) {
try {
    URI uri = new URI("http://www.google.com");
    URL url = uri.toURL(); 
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestProperty("User-Agent", "Chrome");
try (BufferedReader in = new BufferedReader(
    new InputStreamReader(conn.getInputStream()));
    BufferedWriter out = new BufferedWriter(
    new FileWriter("google.txt"))) {
    String line;
        while ((line = in.readLine()) != null) {
            out.write(line);
            out.newLine();
        }
    }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}