import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        String serverIp = args.length > 0 ? args[0] : "localhost";
        Socket socket = new Socket(serverIp, 5050);
        System.out.println("Connected to server.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String msg;
        while (true) {
            System.out.print("You: ");
            msg = console.readLine();
            out.println(msg);
            if (msg.equalsIgnoreCase("bye")) break;
            String reply = in.readLine();
            if (reply == null) break;
            System.out.println("Server: " + reply);
        }

        socket.close();
        System.out.println("Disconnected.");
    }
}