import Core.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    static Date date1, date2, date3, date4, date5, date6, date7;

    @BeforeAll
    static void setUp() {
        date1 = new Date(2021, 2,23);
        date2 = new Date(2021, 2, 22);
        date3 = new Date(2021, 3, 23);
        date4 = new Date(2021, 3, 22);
        date5 = new Date(2021, 1, 23);
        date6 = new Date(2022, 2, 23);
        date7 = new Date(2020, 2, 23);
    }

    @Test
    void equalDateTest() {
        Date dateTest = new Date(2021, 2, 23);
        assertEquals(0, date1.compareTo(dateTest));
    }

    @Test
    void lessThanDateTest() {
        assertAll("Test less than dates",
                () -> assertEquals(-1, date2.compareTo(date1)),
                () -> assertEquals(-1, date5.compareTo(date1)),
                () -> assertEquals(-1, date7.compareTo(date1)),
                () -> assertEquals(-1, date7.compareTo(date2)),
                () -> assertEquals(-1, date7.compareTo(date3)),
                () -> assertEquals(-1, date7.compareTo(date6))
        );
    }

    @Test
    void greaterThanDateTest() {
        assertAll("Test greater than dates",
                () -> assertEquals(1, date1.compareTo(date2)),
                () -> assertEquals(1, date1.compareTo(date5)),
                () -> assertEquals(1, date1.compareTo(date7)),
                () -> assertEquals(1, date2.compareTo(date7)),
                () -> assertEquals(1, date3.compareTo(date7)),
                () -> assertEquals(1, date6.compareTo(date7))
        );
    }

    @Test
    void toStringTest() {
        assertEquals("01-01-0020", new Date(20,1,1).toString());
    }

}
