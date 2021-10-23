package Corba.Admin;

public interface IAdminServer {

    String createRoom (short roomNumber, Corba.CampusServerApp.Date date, Corba.CampusServerApp.TimeSlot[] timeSlotSet, String id);

    String deleteRoom (short roomNumber, Corba.CampusServerApp.Date date, Corba.CampusServerApp.TimeSlot[] timeSlotSet, String id);

}
