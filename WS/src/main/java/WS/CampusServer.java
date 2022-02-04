package WS;


import Core.Campus;
import Core.Date;
import Core.TimeSlot;
import WS.Admin.IAdminServer;
import WS.Student.IStudentServer;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.Set;

@WebService
public interface CampusServer extends IStudentServer, IAdminServer {

    @WebMethod
    @Override
    String createRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id);

    @WebMethod
    @Override
    String deleteRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id);

    @WebMethod
    @Override
    String getAvailableTimeSlot(Date date, String id);

    @WebMethod
    @Override
    String bookRoom(Campus campusName, int roomNumber, Date date, TimeSlot timeSlot, String id);

    @WebMethod
    @Override
    String cancelBooking(String bookingID, String id);

}
