import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;

public class CampusAdminClient implements ICampusAdminClient {

    private String adminID;
    private Campus campusLocation;
    private Logger logger;
    private final IAdminServer server;

    public CampusAdminClient(String adminID) throws RemoteException, NotBoundException {
        this.adminID = adminID;
        this.campusLocation = Campus.valueOf(adminID.substring(0, 3));
        Registry registry = LocateRegistry.getRegistry(1234);
        this.server = (IAdminServer) registry.lookup("rmi://localhost:1234/" + campusLocation.toString());
    }

    @Override
    public String createRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet) throws RemoteException {
        return this.server.createRoom(roomNumber, date, timeSlotSet, this.adminID);
    }

    @Override
    public String deleteRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet) throws RemoteException {
        return this.server.deleteRoom(roomNumber, date, timeSlotSet, this.adminID);
    }

    @Override
    public String sayHello() {
        return "Hello there!";
    }

}
