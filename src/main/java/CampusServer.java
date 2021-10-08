import java.io.IOException;
import java.net.MulticastSocket;
import java.nio.channels.MulticastChannel;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

public class CampusServer extends UnicastRemoteObject implements IAdminServer, IStudentServer {

    Database database;
    Campus campusLocation;

    protected CampusServer(Campus campusLocation) throws RemoteException {
        super();
        this.database = new Database();
        this.campusLocation = campusLocation;
    }

    @Override
    public String createRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id) throws RemoteException {
        timeSlotSet.forEach(timeSlot -> this.database.addTimeSlot(date, roomNumber, timeSlot));
        return "created room record for " + this.database.getRoomRecords(date, roomNumber).stream().map(RoomRecord::toString);
    }

    @Override
    public String deleteRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id) throws RemoteException {
        this.database.deleteRoomRecords(date, roomNumber, timeSlotSet);
        return "deleted room record for " + this.database.getRoomRecords(date, roomNumber).stream().map(RoomRecord::toString);
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello there!";
    }

    @Override
    public RoomRecord bookRoom(String campusName, int roomNumber, Date date, TimeSlot timeSlot, String id) throws RemoteException {
        return null;
    }

    @Override
    public String getAvailableTimeSlot(Date date, String id) throws RemoteException {
        return null;
    }

    @Override
    public String cancelBooking(String bookingID, String id) throws RemoteException {
        return null;
    }
}
