package WS.Student;

import Core.Campus;
import Core.Date;
import Core.TimeSlot;

public interface IStudentServer {

    String getAvailableTimeSlot(Date date, String id);

    String bookRoom(Campus campusName, int roomNumber, Date date, TimeSlot timeSlot, String id);

    String cancelBooking(String bookingID, String id);
}
