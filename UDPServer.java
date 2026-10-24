import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {

        // Create UDP socket on port 5000
        DatagramSocket socket = new DatagramSocket(5000);
        System.out.println("UDP Server waiting...");

        // Buffer to receive data (max 1024 bytes)
        byte[] buffer = new byte[1024];

        // Packet to receive client data
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        // Receive message from client
        socket.receive(packet);

        // Convert received bytes to string
        String message = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Received: " + message);

        // Wait 6 milliseconds
        Thread.sleep(6);

        // Convert message to uppercase
        String upper = message.toUpperCase();

        // Convert string to bytes
        byte[] sendData = upper.getBytes();

        // Create packet to send back to client
        DatagramPacket sendPacket = new DatagramPacket(
                sendData,
                sendData.length,
                packet.getAddress(),   // client IP
                packet.getPort()       // client port
        );

        // Send response
        socket.send(sendPacket);

        // Close socket
        socket.close();
    }
}
