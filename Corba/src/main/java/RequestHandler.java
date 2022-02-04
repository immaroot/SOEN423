import CampusServerApp.Date;
import CampusServerApp.Time;
import CampusServerApp.TimeSlot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class RequestHandler implements Runnable {

    private final CampusServer server;
    private final DatagramPacket packet;
    DatagramSocket socket;
    InetAddress responseAddress;
    int port;
    String request;
    String response;


    public RequestHandler(CampusServer server, DatagramPacket packet) throws SocketException {
        this.packet = packet;
        this.server = server;
        this.socket = server.socket;
        this.responseAddress = packet.getAddress();
        this.port = packet.getPort();
        this.request = new String(packet.getData(), 0, packet.getLength());

    }

    public void run() {

        System.out.println(request);
        String[] args = request.split("\\s");

        switch (args[0]) {
            case "GET_COUNT":
                getCount(args);
                break;
            case "BOOK_ROOM":
                bookRoom(args);
                break;
            case "CANCEL_ROOM":
                cancelRoom(args);
                break;
        }
    }

    private void getCount(String[] args) {
        if (args.length == 2 && args[1].matches("\\d{2}-\\d{2}-\\d{4}")) {
            int day = Integer.parseInt(args[1].split("-")[0]);
            int month = Integer.parseInt(args[1].split("-")[1]);
            int year = Integer.parseInt(args[1].split("-")[2]);
            response = "Hello from " + EnumHelper.getCampusName(server.campusLocation) + "\n" + server.getTimeSlotCount(new Date( year,  month,  day)) + "\n";
        } else {
            response = "Error";
        }
        byte[] buff = response.getBytes();
        DatagramPacket response = new DatagramPacket(buff, buff.length, responseAddress, port);

        try {
            socket.send(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bookRoom(String[] args) {
        if (args.length == 6) {
            int roomNumber = Integer.parseInt(args[1]);
            int day = Integer.parseInt(args[2].split("-")[0]);
            int month = Integer.parseInt(args[2].split("-")[1]);
            int year = Integer.parseInt(args[2].split("-")[2]);
            int startHour = Integer.parseInt(args[3].split(":")[0]);
            int startMin = Integer.parseInt(args[3].split(":")[1]);
            int endHour = Integer.parseInt(args[4].split(":")[0]);
            int endMin = Integer.parseInt(args[4].split(":")[1]);
            String id = args[5];
            
            response = server.bookRoom(
                    server.campusLocation, 
                     roomNumber, 
                    new Date( day,  month,  year), 
                    new TimeSlot(new Time( startHour,  startMin), new Time( endHour,  endMin)), id);
        } else {
            response = "ERROR";
        }
        byte[] buff = response.getBytes();
        DatagramPacket response = new DatagramPacket(buff, buff.length, responseAddress, port);

        try {
            socket.send(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cancelRoom(String[] args) {
        if (args.length == 3) {
            response = server.cancelBooking(args[1], args[2]);
        } else {
            response = "ERROR";
        }
        byte[] buff = response.getBytes();
        DatagramPacket response = new DatagramPacket(buff, buff.length, responseAddress, port);

        try {
            socket.send(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

