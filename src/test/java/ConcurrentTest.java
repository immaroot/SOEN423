//import Core.Campus;
//import RMI.CampusServer;
//
//import java.io.IOException;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//
//public class ConcurrentTest {
//    public static void main(String[] args) throws IOException {
//        CampusServer server1 = new CampusServer(Campus.KKL);
//        new Thread(server1).start();
//        CampusServer server2 = new CampusServer(Campus.WST);
//        new Thread(server2).start();
//        CampusServer server3 = new CampusServer(Campus.DVL);
//        new Thread(server3).start();
//
//        System.out.println("Started server threads");
//
//        byte[] buf;
//        InetAddress address = null;
//        try {
//            address = InetAddress.getLocalHost();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        String message = "GET_COUNT 01-01-1990";
//
//        ArrayList<DatagramSocket> sockets = new ArrayList<>();
//
//        DatagramSocket socket = new DatagramSocket();
//
//        for (int i=0; i < 4; i++) {
//            for (Campus campus : Campus.values()) {
//                int serverPort = 6000 + campus.getIndex();
//                buf = message.getBytes();
//                try {
//                    System.out.println("Sending packet to server " + campus);
//                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, serverPort);
//                    socket.send(packet);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        while (true) {
//            byte[] receiveByte = new byte[256];
//            DatagramPacket packet = new DatagramPacket(receiveByte, receiveByte.length);
//            socket.receive(packet);
//            String received = new String(packet.getData(), 0, packet.getLength());
//            System.out.println(received);
//        }
//    }
//}
