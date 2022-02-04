package Admin;

public interface ICampusAdminClient {

    String createRoom (int roomNumber, CampusServerApp.Date date, CampusServerApp.TimeSlot[] timeSlotSet);

    String deleteRoom (int roomNumber, CampusServerApp.Date date, CampusServerApp.TimeSlot[] timeSlotSet);

}
