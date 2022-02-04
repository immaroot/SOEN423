package Student;

public interface IStudentServer {

    String getAvailableTimeSlot (CampusServerApp.Date date, String id);

    String bookRoom (CampusServerApp.Campus campusName, int roomNumber, CampusServerApp.Date date, CampusServerApp.TimeSlot timeSlot, String id);

    String cancelBooking (String bookingID, String id);

}
