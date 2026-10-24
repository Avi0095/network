import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {

        // Create UDP socket
        DatagramSocket socket = new DatagramSocket();

        // Take input from user
        BufferedReader input = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Enter message: ");
        String message = input.readLine();

        // Convert message to bytes
        byte[] sendData = message.getBytes();

        // Server IP (localhost)
        InetAddress ip = InetAddress.getByName("localhost");

        // Create packet to send to server
        DatagramPacket packet = new DatagramPacket(
                sendData,
                sendData.length,
                ip,
                5000
        );

        // Send message to server
        socket.send(packet);

        // Buffer to receive response
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

        // Receive response from server
        socket.receive(receivePacket);

        // Convert response to string
        String response = new String(
                receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("Server response: " + response);

        // Close socket
        socket.close();
    }
}
