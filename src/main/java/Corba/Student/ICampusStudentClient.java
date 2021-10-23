package Corba.Student;

public interface ICampusStudentClient {

    String getAvailableTimeSlot (Corba.CampusServerApp.Date date);

    String bookRoom (Corba.CampusServerApp.Campus campusName, short roomNumber, Corba.CampusServerApp.Date date, Corba.CampusServerApp.TimeSlot timeSlot);

    String cancelBooking (String bookingID);

}
