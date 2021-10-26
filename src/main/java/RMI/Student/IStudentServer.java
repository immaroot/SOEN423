package RMI.Student;

import Core.Campus;
import Core.Date;
import Core.RoomRecord;
import Core.TimeSlot;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IStudentServer extends Remote {

    String getAvailableTimeSlot(Date date, String id) throws RemoteException;

    RoomRecord bookRoom(Campus campusName, int roomNumber, Date date, TimeSlot timeSlot, String id) throws RemoteException;

    RoomRecord cancelBooking(String bookingID, String id) throws RemoteException;
}
