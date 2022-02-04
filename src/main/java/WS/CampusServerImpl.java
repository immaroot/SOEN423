package WS;

import Core.Campus;
import Core.Date;
import Core.TimeSlot;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.Set;

@WebService
public class CampusServerImpl implements CampusServer {

    public CampusServerImpl() {
    }

    @WebMethod
    @Override
    public String createRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id) {
        return "hello";
    }

    @Override
    public String deleteRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id) {
        return "hi";
    }

    @Override
    public String getAvailableTimeSlot(Date date, String id) {
        return "Howdy";
    }

    @Override
    public String bookRoom(Campus campusName, int roomNumber, Date date, TimeSlot timeSlot, String id) {
        return "allo";
    }

    @Override
    public String cancelBooking(String bookingID, String id) {
        return "noooo";
    }
}
