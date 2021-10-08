import java.rmi.RemoteException;

public class StudentClient implements IStudentServer {

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

    @Override
    public String sayHello() throws RemoteException {
        return null;
    }
}
