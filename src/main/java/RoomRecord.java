public class RoomRecord {
    private final Date date;
    private final int roomNumber;

    public String getRoomRecordId() {
        return roomRecordId;
    }

    private final TimeSlot timeSlot;
    private String bookedBy;
    private final String roomRecordId;
    private static int recordIdNumberCounter = 0;

    public RoomRecord(Date date, int roomNumber, TimeSlot timeSlot) {
        this.date = date;
        this.roomNumber = roomNumber;
        this.timeSlot = timeSlot;
        this.roomRecordId = "RR" + String.format("%05d", ++recordIdNumberCounter);
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
}