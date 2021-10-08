import java.io.Serializable;

public class RoomRecord implements Serializable {

    private final Date date;
    private final int roomNumber;
    private final TimeSlot timeSlot;
    private final String roomRecordId;
    private String bookedBy;
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

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    @Override
    public String toString() {
        return "RoomRecord{" +
                "date=" + date +
                ", roomNumber=" + roomNumber +
                ", timeSlot=" + timeSlot +
                ", roomRecordId='" + roomRecordId + '\'' +
                ", bookedBy='" + bookedBy + '\'' +
                '}';
    }
}