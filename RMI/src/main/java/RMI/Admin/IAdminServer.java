package RMI.Admin;

import Core.Date;
import Core.TimeSlot;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface IAdminServer extends Remote {

    String createRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id) throws RemoteException;

    String deleteRoom(int roomNumber, Date date, Set<TimeSlot> timeSlotSet, String id) throws RemoteException;

}
