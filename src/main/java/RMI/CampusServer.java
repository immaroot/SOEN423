package RMI;

import Core.*;
import RMI.Admin.IAdminServer;
import RMI.Student.IStudentServer;

import java.io.IOException;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CampusServer extends UnicastRemoteObject implements IAdminServer, IStudentServer, Runnable {

    Database database;
    Campus campusLocation;
    DatagramSocket socket;

    public CampusServer(Campus campusLocation) throws RemoteException {
        super();
        this.database = new Database();
        this.campusLocation = campusLocation;
        try {
            this.socket = new DatagramSocket(6000 + this.campusLocation.getIndex());
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String createRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id) throws RemoteException {
        timeSlotSet.forEach(timeSlot -> this.database.addTimeSlot(date, roomNumber, timeSlot));
        return "created room record for " + this.database.getRoomRecords(date, roomNumber).stream().map(RoomRecord::toString);
    }

    @Override
    public String deleteRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id) throws RemoteException {
        Collection<RoomRecord> roomRecordCollection = this.database.deleteRoomRecords(date, roomNumber, timeSlotSet);
        return "deleted room record for " + roomRecordCollection.stream().map(RoomRecord::toString).collect(Collectors.joining(","));
    }

    @Override
    public RoomRecord bookRoom(Campus campus, int roomNumber, Date date, TimeSlot timeSlot, String id) throws RemoteException {
        if (!this.database.isBooked(date, roomNumber, timeSlot)) {
            this.database.bookTimeSlot(date, roomNumber, timeSlot, id);
            return this.database.getRoomRecord(date, roomNumber, timeSlot);
        } else {
            return null;
        }
    }

    @Override
    public String getAvailableTimeSlot(Date date, String id) throws RemoteException {
        StringBuilder sb = new StringBuilder();
        sb.append(campusLocation.toString())
                .append(": ")
                .append(this.database.getTimeSlotAvailableCount(date))
                .append("\n");
        byte[] buf;
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String message = "GET_COUNT " + date.toString();
        for (Campus campus : Campus.values()) {

            //send request for counts except if this campus
            if (campus != campusLocation) {

                System.out.println("Sending to " + campus);
                System.out.println(message);

                int serverPort = 6000 + campus.getIndex();
                buf = message.getBytes();
                try {
                    DatagramSocket socket = new DatagramSocket();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, serverPort);
                    socket.send(packet);
                    byte[] receiveByte = new byte[256];
                    packet = new DatagramPacket(receiveByte, receiveByte.length);
                    socket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    sb.append(received);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    @Override
    public RoomRecord cancelBooking(String bookingID, String id) throws RemoteException {
        RoomRecord record;
        if (this.database.hasBookingID(bookingID)) {
            record = this.database.getRoomRecordByBookingID(bookingID);
            record.cancelReservation();
            return record;
        } else {
            return null;
        }
    }

    public Campus getCampusLocation() {
        return this.campusLocation;
    }

    public int getTimeSlotCount(Date date) {
        return this.database.getTimeSlotAvailableCount(date);
    }

    public void run() {

        //noinspection InfiniteLoopStatement
        while (true) {
            byte[] buff = new byte[256];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            try {
                socket.receive(packet);
                new Thread(new RequestHandler(this, packet)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
