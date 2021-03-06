package Core;

import java.io.Serializable;
import java.util.UUID;

public class RoomRecord implements Serializable {

    private final Date date;
    private final int roomNumber;
    private final TimeSlot timeSlot;
    private final String roomRecordId;
    private String bookedBy;
    private String bookingID;
    private static int recordIdNumberCounter = 0;

    public RoomRecord(Date date, int roomNumber, TimeSlot timeSlot) {
        this.date = date;
        this.roomNumber = roomNumber;
        this.timeSlot = timeSlot;
        this.roomRecordId = "RR" + String.format("%05d", ++recordIdNumberCounter);
    }

    public String getRoomRecordId() {
        return roomRecordId;
    }

    public Date getDate() {
        return date;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public boolean isBooked() {
        return bookedBy != null;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public String generateBookingID(String prefix) {
        UUID uuid = UUID.randomUUID();
        bookingID = prefix + uuid;
        return bookingID;
    }

    public String getBookingID() {
        return bookingID;
    }


    public void cancelReservation() {
        bookingID = null;
        bookedBy = null;
    }

    @Override
    public String toString() {
        return "Core.RoomRecord{" +
                "date=" + date +
                ", roomNumber=" + roomNumber +
                ", timeSlot=" + timeSlot +
                ", roomRecordId='" + roomRecordId + '\'' +
                ", bookedBy='" + bookedBy + '\'' +
                '}';
    }
}