import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RoomRecordTest {

    static Date date1, date2;
    static int roomNum1, roomNum2;
    static TimeSlot timeSlot1, timeSlot2;
    static RoomRecord roomRecord1, roomRecord2;

    @BeforeEach
    void setUp() {
        date1 = new Date(2021, 9, 23);
        date2 = new Date(2021, 9, 24);
        roomNum1 = 201;
        roomNum2 = 301;
        timeSlot1 = new TimeSlot(new Time(8,0), new Time(9,0));
        timeSlot2 = new TimeSlot(new Time(9,0), new Time(10,0));
        roomRecord1 = new RoomRecord(date1, roomNum1, timeSlot1);
        roomRecord2 = new RoomRecord(date1, roomNum1, timeSlot2);
    }

    @Test
    void roomRecordIdTest() {
        assertEquals("RR00001", roomRecord1.getRoomRecordId());
        assertEquals("RR00002", roomRecord2.getRoomRecordId());
    }

    @Test
    void getDate() {
        assertEquals(date1, roomRecord1.getDate());
        assertEquals(date1, roomRecord2.getDate());
    }

    @Test
    void getRoomNumber() {
        assertEquals(roomNum1, roomRecord1.getRoomNumber());
    }

    @Test
    void getTimeSlot() {
        assertEquals(timeSlot1, roomRecord1.getTimeSlot());
        assertEquals(timeSlot2, roomRecord2.getTimeSlot());
    }

    @Test
    void getBookedBy() {
        assertNull(roomRecord1.getBookedBy());
    }

    @Test
    void setBookedBy() {
        roomRecord1.setBookedBy("30");
        assertEquals("30", roomRecord1.getBookedBy());
    }
}