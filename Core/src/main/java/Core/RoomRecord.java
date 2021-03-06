package Core;

import Core.Date;
import Core.TimeSlot;

import java.io.Serializable;
import java.util.UUID;

public class RoomRecord implements Serializable {

    private final Date date;
    private final int roomNumber;
    private final TimeSlot timeSlot;
    private final String roomRecordID;
    private String bookedBy;
    private String bookingID;
    private static int recordIdNumberCounter = 0;

    public RoomRecord(Date date, int roomNumber, TimeSlot timeSlot) {
        this.date = date;
        this.roomNumber = roomNumber;
        this.timeSlot = timeSlot;
        this.roomRecordID = "RR" + String.format("%05d", ++recordIdNumberCounter);
    }

    public String getBookingID() {
        return bookingID;
    }

    public String bookRoom(String location, String studentID) throws BookingException {
        if (!isBooked()) {
            UUID uuid = UUID.randomUUID();
            bookingID = location + uuid;
            bookedBy = studentID;
            return bookingID;
        } else {
            throw new BookingException("The room is already booked.");
        }
    }

    public void cancelReservation(String bookingID) throws BookingException {
        if (isBooked() && this.bookingID.equals(bookingID)) {
            this.bookingID = null;
            this.bookedBy = null;
        } else {
            throw new BookingException("The bookingID does not match.");
        }
    }

    public boolean isBooked() {
        return this.bookedBy != null;
    }

    @Override
    public String toString() {
        return "RoomRecord{" +
                "date=" + date +
                ", roomNumber=" + roomNumber +
                ", timeSlot=" + timeSlot +
                ", roomRecordId='" + roomRecordID + '\'' +
                ", bookedBy='" + bookedBy + '\'' +
                '}';
    }
}