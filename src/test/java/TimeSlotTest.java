import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {
    static Time start;
    static Time end;
    static TimeSlot timeSlot;

    @BeforeAll
    static void setUp() {
        start = new Time(8, 0);
        end = new Time(9,0);
        timeSlot = new TimeSlot(start, end);
    }

    @Test
    void getStart() {
        Time startTest = new Time(8,0);
        assertEquals(startTest, timeSlot.getStart());
    }

    @Test
    void getEnd() {
        Time endTest = new Time(9,0);
        assertEquals(endTest, timeSlot.getEnd());
    }

    @Test
    void overlaps() {
        TimeSlot overlappingEqual = new TimeSlot(start, end);
        TimeSlot overlappingStart = new TimeSlot(new Time(8,30), new Time(10,0));
        TimeSlot overlappingEnd = new TimeSlot(new Time(7,30), new Time(8, 30));
        TimeSlot nonOverlapping = new TimeSlot(new Time(9,30), new Time(10,30));
        assertTrue(overlappingEqual.overlaps(timeSlot));
        assertTrue(overlappingStart.overlaps(timeSlot));
        assertTrue(overlappingEnd.overlaps(timeSlot));
        assertFalse(nonOverlapping.overlaps(timeSlot));
    }

    @Test
    void testEquals() {
        TimeSlot equalTimeSlot = new TimeSlot(start, end);
        assertEquals(equalTimeSlot, timeSlot);
    }
}