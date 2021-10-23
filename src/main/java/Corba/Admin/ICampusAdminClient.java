package Corba.Admin;

public interface ICampusAdminClient {

    String createRoom (short roomNumber, Corba.CampusServerApp.Date date, Corba.CampusServerApp.TimeSlot[] timeSlotSet);

    String deleteRoom (short roomNumber, Corba.CampusServerApp.Date date, Corba.CampusServerApp.TimeSlot[] timeSlotSet);

}
