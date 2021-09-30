import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;


class RoomRecordTest {

    private static RoomRecord roomRecord;

    @BeforeEach
    void setUp() {
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.SEPTEMBER, 23);

        int roomNumber = 203;

        Calendar startTime = Calendar.getInstance();
        startTime.set(2020, Calendar.SEPTEMBER, 23, 8, 0);

        Calendar endTime = Calendar.getInstance();
        endTime.set(2020, Calendar.SEPTEMBER, 23, 9, 0);

        TimeSlot timeSlot = new TimeSlot(startTime, endTime);

        roomRecord = new RoomRecord(date, roomNumber, timeSlot);

    }


    @Test
    void getDate() {
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.SEPTEMBER, 23);
        assertAll("Assert same date",
                () -> assertEquals(date.get(Calendar.YEAR), roomRecord.getDate().get(Calendar.YEAR)),
                () -> assertEquals(date.get(Calendar.MONTH), roomRecord.getDate().get(Calendar.MONTH)),
                () -> assertEquals(date.get(Calendar.DATE), roomRecord.getDate().get(Calendar.DATE))
        );

    }

    @Test
    void getRoomNumber() {
        assertEquals(203, roomRecord.getRoomNumber());
    }

    @Test
    void getTimeSlot() {
        Calendar startTime = Calendar.getInstance();
        startTime.set(2020, Calendar.SEPTEMBER, 23, 8, 0);

        Calendar endTime = Calendar.getInstance();
        endTime.set(2020, Calendar.SEPTEMBER, 23, 9, 0);

        TimeSlot timeSlot = new TimeSlot(startTime, endTime);
        assertEquals(timeSlot, roomRecord.getTimeSlot());
    }

    @Test
    void getBookedBy() {
        assertEquals(0, roomRecord.getBookedBy());
    }

    @Test
    void setBookedBy() {
        roomRecord.setBookedBy(30);
        assertEquals(30, roomRecord.getBookedBy());
    }
}