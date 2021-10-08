import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CampusStudentClient implements ICampusStudentClient {


    private String studentID;
    private Campus campusLocation;
    private Logger logger;
    Registry registry;
    private final IStudentServer server;

    public CampusStudentClient(String studentID) throws RemoteException, NotBoundException {
        this.studentID = studentID;
        this.campusLocation = Campus.valueOf(studentID.substring(0, 3));
        this.registry = LocateRegistry.getRegistry(1234);
        this.server = (IStudentServer) registry.lookup("rmi://localhost:1234/" + campusLocation.toString());
    }

    @Override
    public RoomRecord bookRoom(String campusName, int roomNumber, Date date, TimeSlot timeSlot) throws RemoteException {
        RoomRecord record = null;
        try {
            IStudentServer requestedServer = (IStudentServer) registry.lookup("rmi://localhost:1234/" + campusName);
            record = requestedServer.bookRoom(campusName, roomNumber, date, timeSlot, this.studentID);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        return record;
    }

    @Override
    public String getAvailableTimeSlot(Date date) throws RemoteException {
        return this.server.getAvailableTimeSlot(date, this.studentID);
    }

    @Override
    public String cancelBooking(String bookingID) throws RemoteException {
        return this.server.cancelBooking(bookingID, this.studentID);
    }
}
