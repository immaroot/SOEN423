public class RoomRecord {
    private final Date date;
    private final int roomNumber;
    private final TimeSlot timeSlot;
    private int bookedBy;

    public RoomRecord(Date date, int roomNumber, TimeSlot timeSlot) {
        this.date = date;
        this.roomNumber = roomNumber;
        this.timeSlot = timeSlot;
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

    public int getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(int bookedBy) {
        this.bookedBy = bookedBy;
    }
}