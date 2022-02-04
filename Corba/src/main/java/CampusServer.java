import Admin.IAdminServer;
import CampusServerApp.Campus;
import CampusServerApp.CampusServerPOA;
import CampusServerApp.Date;
import CampusServerApp.TimeSlot;
import Core.BookingException;
import Core.Database;
import Core.RoomRecord;
import Core.Time;
import Student.IStudentServer;
import org.omg.CORBA.ORB;

import java.io.IOException;
import java.net.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CampusServer extends CampusServerPOA implements IAdminServer, IStudentServer, Runnable {

    Database database;
    Campus campusLocation;
    DatagramSocket socket;
    ORB orb;

    public CampusServer(Campus campusLocation) {
        super();
        String campusName = EnumHelper.getCampusName(campusLocation);
        this.database = new Database(Core.Campus.valueOf(campusName));
        this.campusLocation = campusLocation;
        try {
            this.socket = new DatagramSocket(6000 + EnumHelper.getCampusValue(campusLocation));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAvailableTimeSlot(Date date, String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(EnumHelper.getCampusName(campusLocation))
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
        String message = "GET_COUNT " + date.toString();
        for (Core.Campus campus : Core.Campus.values()) {

            //send request for counts except if this campus
            if (campus.getIndex()  != EnumHelper.getCampusValue(campusLocation)) {

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
    public String bookRoom(Campus campusName, int roomNumber, Date date, TimeSlot timeSlot, String id) {
        if (EnumHelper.getCampusValue(campusName) != EnumHelper.getCampusValue(campusLocation)) {
            byte[] buf;
            InetAddress address = null;
            try {
                address = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            String message = "BOOK_ROOM "
                    + roomNumber + " "
                    + date.toString() + " "
                    + timeSlot.start.hour + ":" + timeSlot.start.minute + " "
                    + timeSlot.end.hour + ":" + timeSlot.end.minute + " "
                    + id;
            System.out.println("Sending request to server" + EnumHelper.getCampusValue(campusName));
            int serverPort = 6000 + EnumHelper.getCampusValue(campusName);
            buf = message.getBytes();
            try {
                DatagramSocket socket = new DatagramSocket();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, serverPort);
                socket.send(packet);
                byte[] receiveByte = new byte[256];
                packet = new DatagramPacket(receiveByte, receiveByte.length);
                socket.receive(packet);
                return new String(packet.getData(), 0, packet.getLength());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                String bookingID = this.database.makeBooking(convertDate(date), roomNumber, convertTimeSlot(timeSlot), id);
                return "SUCCESS: " + bookingID;
            } catch (BookingException e) {
                return "ERROR";
            }
        }
        return "ERROR";
    }

    @Override
    public String cancelBooking(String bookingID, String id) {
        try {
            this.database.cancelBooking(bookingID);
            return "Your reservation has been canceled successfully.";
        } catch (BookingException e) {
            e.printStackTrace();
            return "Cannot cancel a reservation that has not been done.";
        }
    }

    @Override
    public String createRoom(int roomNumber, Date date, TimeSlot[] timeSlots, String id) {
        Set<Core.TimeSlot> timeSlotSet = convertTimeSlotArray(timeSlots);
        timeSlotSet.forEach(timeSlot -> this.database.addTimeSlot(convertDate(date), roomNumber, timeSlot));
        return "created room record for " + this.database.;
    }

    @Override
    public String deleteRoom(int roomNumber, Date date, TimeSlot[] timeSlots, String id) {
        Set<Core.TimeSlot> timeSlotSet = convertTimeSlotArray(timeSlots);
        String message;
        try {
            message = "deleted room record for " + this.database.deleteRoomRecords(convertDate(date), roomNumber, timeSlotSet);
        } catch (BookingException e) {
            e.printStackTrace();
            message = "ERROR";
        }
        return message;
    }

    private Core.Date convertDate(Date date) {
        return new Core.Date(date.year, date.month, date.day);
    }

    private Core.Time convertTime(CampusServerApp.Time time) {
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
