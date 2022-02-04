package WS.Admin;

import Core.Date;
import Core.TimeSlot;
import WS.CampusServer;
import WS.CampusServerImpl;
import jakarta.xml.ws.WebServiceRef;

public class CampusAdminClient implements ICampusAdminClient {

    String id;

    public CampusAdminClient(String id) {


    }

    @Override
    public String createRoom(int roomNumber, Date date, TimeSlot[] timeSlotSet) {
        return null;
    }

    @Override
    public String deleteRoom(int roomNumber, Date date, TimeSlot[] timeSlotSet) {
        return null;
    }
}
