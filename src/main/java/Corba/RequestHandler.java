package Corba;

import Corba.CampusServerApp.Date;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class RequestHandler implements Runnable {

    private final CampusServer server;
    private final DatagramPacket packet;

    public RequestHandler(CampusServer server, DatagramPacket packet) throws SocketException {
        this.packet = packet;
        this.server = server;

    }

    public void run() {
        DatagramSocket socket = server.socket;
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        String received = new String(packet.getData(), 0, packet.getLength());

        String message;
        System.out.println(received);
        String[] args = received.split("\\s");
        if (args.length == 2 && args[0].matches("GET_COUNT") && args[1].matches("\\d{2}-\\d{2}-\\d{4}")) {
            int day = Integer.parseInt(args[1].split("-")[0]);
            int month = Integer.parseInt(args[1].split("-")[1]);
            int year = Integer.parseInt(args[1].split("-")[2]);
            message = "Hello from " + server.campusLocation.toString() + "\n" + server.getTimeSlotCount(new Date((short) year, (short) month, (short) day)) + "\n";
        } else {
            message = "Error";
        }
        byte[] buff = message.getBytes();
        DatagramPacket response = new DatagramPacket(buff, buff.length, address, port);

        try {
            socket.send(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

