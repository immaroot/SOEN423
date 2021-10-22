import Core.Time;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {
    static Time time1, time2, time3, time4;

    @BeforeAll
    static void setUp() {
        time1 = new Time(8,0);
        time2 = new Time(9,0);
        time3 = new Time(8, 30);
        time4 = new Time(9,0);
    }

    @Test
    void equalTimeTest() {
        assertEquals(0, time2.compareTo(time4));
    }

    @Test
    void lessThanTimeTest() {
        assertAll("Test less than time:",
                () -> assertEquals(-1, time1.compareTo(time2)),
                () -> assertEquals(-1, time3.compareTo(time2)),
                () -> assertEquals(-1, time1.compareTo(time3))
        );
    }
    
    @Test
    void greaterThanTimeTest() {
        assertAll("Test less than time:",
                () -> assertEquals(1, time2.compareTo(time1)),
                () -> assertEquals(1, time2.compareTo(time3)),
                () -> assertEquals(1, time3.compareTo(time1))
        );
    }

    @Test
    void testEqual() {
        assertEquals(new Time(9,0), new Time(9,0));
    }

    @Test
    void testHashCode() {
        assertEquals(new Time(9,0).hashCode(), new Time(9,0).hashCode());
    }
}
