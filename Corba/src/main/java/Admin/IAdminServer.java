package Admin;

public interface IAdminServer {

    String createRoom (int roomNumber, CampusServerApp.Date date, CampusServerApp.TimeSlot[] timeSlotSet, String id);

    String deleteRoom (int roomNumber, CampusServerApp.Date date, CampusServerApp.TimeSlot[] timeSlotSet, String id);

}
