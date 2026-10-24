import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {

        // Connect to server (localhost, port 5000)
        Socket socket = new Socket("localhost", 5000);

        // Read input from keyboard
        BufferedReader userInput = new BufferedReader(
                new InputStreamReader(System.in));

        // Send data to server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Receive response from server
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        // Take user message
        System.out.print("Enter message: ");
        String message = userInput.readLine();

        // Send message to server
        out.println(message);

        // Read response from server
        String response = in.readLine();
        System.out.println("Server response: " + response);

        // Close connection
        socket.close();
    }
}
