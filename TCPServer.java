import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {

        // Create server socket on port 5000
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Server is waiting...");

        // Wait for client connection
        Socket socket = server.accept();
        System.out.println("Client connected");

        // Input from client
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        // Output to client
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Read message from client
        String message = in.readLine();
        System.out.println("Received: " + message);

        // Convert to uppercase and send back
        out.println(message.toUpperCase());

        // Close connections
        socket.close();
        server.close();
    }
}
