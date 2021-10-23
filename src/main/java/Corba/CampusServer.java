package Corba;


import Corba.Admin.IAdminServer;
import Corba.CampusServerApp.*;
import Corba.CampusServerApp.Date;
import Corba.Student.IStudentServer;
import Core.Database;
import Core.RoomRecord;
import Core.Time;
import org.omg.CORBA.ORB;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

public class CampusServer extends CampusServerPOA implements IAdminServer, IStudentServer, Runnable {

    Database database;
    Campus campusLocation;
    DatagramSocket socket;
    ORB orb;

    public CampusServer(Campus campusLocation) {
        super();
        this.database = new Database();
        this.campusLocation = campusLocation;
        try {
            this.socket = new DatagramSocket(6000 + campusLocation.value());
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAvailableTimeSlot(Date date, String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(campusLocation.toString())
                .append(": ")
                .append(this.database.getTimeSlotAvailableCount(convertDate(date)))
                .append("\n");
        byte[] buf;
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String message = "GET_COUNT " + date;
        for (Core.Campus campus : Core.Campus.values()) {

            //send request for counts except if this campus
            if ((campus.getIndex() - 1)  != campusLocation.value()) {

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
    public String bookRoom(Campus campusName, short roomNumber, Date date, TimeSlot timeSlot, String id) {

        if (!this.database.isBooked(convertDate(date), roomNumber, convertTimeSlot(timeSlot))) {
            this.database.bookTimeSlot(convertDate(date), roomNumber, convertTimeSlot(timeSlot), id);
            return "Your time has been booked successfully with bookingID: " + this.database.getRoomRecord(convertDate(date), roomNumber, convertTimeSlot(timeSlot)).getRoomRecordId();
        } else {
            return "Cannot book this timeslot since it is already booked";
        }
    }

    @Override
    public String cancelBooking(String bookingID, String id) {
        Core.RoomRecord record;
        if (this.database.hasBookingID(bookingID)) {
            record = this.database.getRoomRecordByBookingID(bookingID);
            record.cancelReservation();
            return "Your reservation has been canceled successfully.";
        } else {
            return "Cannot cancel a reservation that has not been done.";
        }
    }

    @Override
    public String createRoom(short roomNumber, Date date, TimeSlot[] timeSlots, String id) {
        Set<Core.TimeSlot> timeSlotSet = convertTimeSlotArray(timeSlots);
        timeSlotSet.forEach(timeSlot -> this.database.addTimeSlot(convertDate(date), roomNumber, timeSlot));
        return "created room record for " + this.database.getRoomRecords(convertDate(date), roomNumber).stream().map(RoomRecord::toString);
    }

    @Override
    public String deleteRoom(short roomNumber, Date date, TimeSlot[] timeSlots, String id) {
        Set<Core.TimeSlot> timeSlotSet = convertTimeSlotArray(timeSlots);
        Collection<RoomRecord> roomRecordCollection = this.database.deleteRoomRecords(convertDate(date), roomNumber, timeSlotSet);
        return "deleted room record for " + roomRecordCollection.stream().map(RoomRecord::toString).collect(Collectors.joining(","));
    }

    private Core.Date convertDate(Date date) {
        return new Core.Date(date.year, date.month, date.day);
    }

    private Core.Time convertTime(Corba.CampusServerApp.Time time) {
        return new Time(time.hour, time.minute);
    }

    private Core.TimeSlot convertTimeSlot(TimeSlot timeSlot) {
        return new Core.TimeSlot(convertTime(timeSlot.start), convertTime(timeSlot.end));
    }

    private Set<Core.TimeSlot> convertTimeSlotArray(TimeSlot[] timeSlots) {
        Set<Core.TimeSlot> timeSlotSet = new HashSet<>();
        for (TimeSlot timeSlot : timeSlots) {
            timeSlotSet.add(convertTimeSlot(timeSlot));
        }
        return timeSlotSet;
    }

    public int getTimeSlotCount(Date date) {
        return this.database.getTimeSlotAvailableCount(convertDate(date));
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

    public void setOrb(ORB orb) {
        this.orb = orb;
    }
}
