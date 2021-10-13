import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.net.*;
import java.rmi.RemoteException;

class RequestHandlerTest {

    public static void main(String[] args) throws RemoteException {

        CampusServer server1 = new CampusServer(Campus.KKL);
        new Thread(server1).start();
        CampusServer server2 = new CampusServer(Campus.WST);
        new Thread(server2).start();
        CampusServer server3 = new CampusServer(Campus.DVL);
        new Thread(server3).start();

        System.out.println("Started server threads");

        byte[] buf;
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String message = "GET_COUNT 01-01-1990";

        for (Campus campus : Campus.values()) {
            int serverPort = 6000 + campus.getIndex();
            buf = message.getBytes();
            try {
                System.out.println("Sending packet to server " + campus);
                DatagramSocket socket = new DatagramSocket();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, serverPort);
                socket.send(packet);
                byte[] receiveByte = new byte[256];
                packet = new DatagramPacket(receiveByte, receiveByte.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + received);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}