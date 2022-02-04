package WS.Admin;

import Core.Date;
import Core.TimeSlot;

public interface ICampusAdminClient {

    String createRoom (int roomNumber, Date date, TimeSlot[] timeSlotSet);

    String deleteRoom (int roomNumber, Date date, TimeSlot[] timeSlotSet);

}
