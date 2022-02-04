package Student;

public interface ICampusStudentClient {

    String getAvailableTimeSlot (CampusServerApp.Date date);

    String bookRoom (CampusServerApp.Campus campusName, int roomNumber, CampusServerApp.Date date, CampusServerApp.TimeSlot timeSlot);

    String cancelBooking (String bookingID);

}
