import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IStudentServer extends Remote {

    RoomRecord bookRoom(String campusName, int roomNumber, Date date, TimeSlot timeSlot, String id) throws RemoteException;

    String getAvailableTimeSlot(Date date, String id) throws RemoteException;

    String cancelBooking(String bookingID, String id) throws RemoteException;
}
