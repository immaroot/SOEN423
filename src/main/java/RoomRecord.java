import java.util.Calendar;

public class RoomRecord {
    private final Calendar date;
    private final int roomNumber;
    private final TimeSlot timeSlot;
    private int bookedBy;

    public RoomRecord(Calendar date, int roomNumber, TimeSlot timeSlot) {
        this.date = date;
        this.roomNumber = roomNumber;
        this.timeSlot = timeSlot;
    }

    public Calendar getDate() {
        return date;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public int getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(int bookedBy) {
        this.bookedBy = bookedBy;
    }
}