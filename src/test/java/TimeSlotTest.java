import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {
    static Calendar start;
    static Calendar end;
    static TimeSlot timeSlot;

    @BeforeEach
    void setUp() {
        start = Calendar.getInstance();
        start.set(2020, Calendar.SEPTEMBER, 23, 8, 0);
        end = start;
        end.add(Calendar.MINUTE, 60);
        timeSlot = new TimeSlot(start, end);
    }

    @Test
    void getStart() {
        Calendar startTime = Calendar.getInstance();
        startTime.set(2020, Calendar.SEPTEMBER, 23, 8, 0);
        assertAll("assert start time:",
                () -> assertEquals(startTime.get(Calendar.YEAR), timeSlot.getStart().get(Calendar.YEAR)),
                () -> assertEquals(startTime.get(Calendar.MONTH), timeSlot.getStart().get(Calendar.MONTH)),
                () -> assertEquals(startTime.get(Calendar.DATE), timeSlot.getStart().get(Calendar.DATE)),
                () -> assertEquals(startTime.get(Calendar.MINUTE), timeSlot.getStart().get(Calendar.MINUTE))
        );
    }

    @Test
    void getEnd() {
        Calendar endTime = Calendar.getInstance();
        endTime.set(2020, Calendar.SEPTEMBER, 23, 8, 0);
        assertAll("assert start time:",
                () -> assertEquals(endTime.get(Calendar.YEAR), timeSlot.getEnd().get(Calendar.YEAR)),
                () -> assertEquals(endTime.get(Calendar.MONTH), timeSlot.getEnd().get(Calendar.MONTH)),
                () -> assertEquals(endTime.get(Calendar.DATE), timeSlot.getEnd().get(Calendar.DATE)),
                () -> assertEquals(endTime.get(Calendar.MINUTE), timeSlot.getEnd().get(Calendar.MINUTE))
        );
    }

    @Test
    void testEquals() {
        Calendar startTime = Calendar.getInstance();
        startTime.set(2020, Calendar.SEPTEMBER, 23, 8, 0);
        Calendar endTime = start;
        endTime.add(Calendar.MINUTE, 60);
        TimeSlot timeSlotTest = new TimeSlot(startTime, endTime);
        assertEquals(timeSlotTest, timeSlot);
    }
}