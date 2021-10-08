import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Collection;

class DatabaseTest {
    static Date date1, date2;
    static int roomNum1, roomNum2;
    static TimeSlot timeSlot1, timeSlot2;
    static Database database;

    @BeforeAll
    static void setUp() {
        date1 = new Date(2021, 9, 23);
        date2 = new Date(2021, 9, 24);
        roomNum1 = 201;
        roomNum2 = 301;
        timeSlot1 = new TimeSlot(new Time(8,0), new Time(9,0));
        timeSlot2 = new TimeSlot(new Time(9,0), new Time(10,0));
        database = new Database();
        database.addDate(date1);
        database.addDate(date2);
        database.addRoom(date1, roomNum1);
        database.addRoom(date1, roomNum2);
        database.addRoom(date2, roomNum1);
        database.addRoom(date2, roomNum2);
        database.addTimeSlot(date1, roomNum1, timeSlot1);
        database.addTimeSlot(date1, roomNum1, timeSlot2);
        database.addTimeSlot(date1, roomNum2, timeSlot1);
        database.addTimeSlot(date1, roomNum2, timeSlot2);
        database.addTimeSlot(date2, roomNum1, timeSlot1);
        database.addTimeSlot(date2, roomNum1, timeSlot2);
        database.addTimeSlot(date2, roomNum2, timeSlot1);
        database.addTimeSlot(date2, roomNum2, timeSlot2);
    }

    @Test
    void addDate() {
    }

    @Test
    void addRoom() {
    }

    @Test
    void addTimeSlot() {
    }

    @Test
    void getDates() {
        Collection<Date> dates = database.getDates();
        assertTrue(dates.contains(date1));
        assertTrue(dates.contains(date2));
    }

    @Test
    void getRooms() {
        Collection<Integer> rooms = database.getRooms(date1);
        assertTrue(rooms.contains(roomNum1));
        assertTrue(rooms.contains(roomNum2));
    }

    @Test
    void getTimeSlots() {
        Collection<TimeSlot> timeSlots = database.getTimeSlots(date1, roomNum1);
        assertTrue(timeSlots.contains(timeSlot1));
        assertTrue(timeSlots.contains(timeSlot2));
    }

    @Test
    void getCount() {
        assertEquals(8, database.getRoomRecordCount());
    }
}