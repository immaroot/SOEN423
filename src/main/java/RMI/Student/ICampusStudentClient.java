package RMI.Student;

import Core.Campus;
import Core.Date;
import Core.TimeSlot;

import java.rmi.RemoteException;

public interface ICampusStudentClient {
    String bookRoom(Campus campusName, int roomNumber, Date date, TimeSlot timeSlot) throws RemoteException;

    String getAvailableTimeSlot(Date date) throws RemoteException;

    String cancelBooking(String bookingID) throws RemoteException;
}
