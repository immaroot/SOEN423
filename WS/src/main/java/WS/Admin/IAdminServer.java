package WS.Admin;



import Core.Date;
import Core.TimeSlot;

import java.util.Set;

public interface IAdminServer {

    String createRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id);

    String deleteRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id);

}
