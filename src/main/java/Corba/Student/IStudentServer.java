package Corba.Student;

public interface IStudentServer {

    String getAvailableTimeSlot (Corba.CampusServerApp.Date date, String id);

    String bookRoom (Corba.CampusServerApp.Campus campusName, short roomNumber, Corba.CampusServerApp.Date date, Corba.CampusServerApp.TimeSlot timeSlot, String id);

    String cancelBooking (String bookingID, String id);

}
