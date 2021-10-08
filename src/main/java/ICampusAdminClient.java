import java.rmi.RemoteException;
import java.util.Set;

public interface ICampusAdminClient {

    String createRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet) throws RemoteException;

    String deleteRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet) throws RemoteException;

    String sayHello();
}
