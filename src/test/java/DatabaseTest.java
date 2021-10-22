import Core.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DatabaseTest {
    static Date date1, date2;
    static int roomNum1, roomNum2;
    static TimeSlot timeSlot1, timeSlot2;
    static Database database;

    @BeforeAll
    public static void setUp() {
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
    public void getDates() {
        Collection<Date> dates = database.getDates();
        assertTrue(dates.contains(date1));
        assertTrue(dates.contains(date2));
    }

    @Test
    public void getRooms() {
        Collection<Integer> rooms = database.getRooms(date1);
        assertTrue(rooms.contains(roomNum1));
        assertTrue(rooms.contains(roomNum2));
    }

    @Test
    public void getTimeSlots() {
        Collection<TimeSlot> timeSlots = database.getTimeSlots(date1, roomNum1);
        assertTrue(timeSlots.contains(timeSlot1));
        assertTrue(timeSlots.contains(timeSlot2));
    }

    @Test
    public void getCount() {
        assertEquals(8, database.getRoomRecordCount());
    }

    @Test
    public void getTimeSlotAvailableCount() {
        assertEquals(4, database.getTimeSlotAvailableCount(date1));
    }

    @Test
    public void getTimeSlotAvailableCountEmpty() {
        Database newDatabase = new Database();
        assertEquals(0, newDatabase.getTimeSlotAvailableCount(date1));
    }

    @Test
    public void deleteTimeSlotTest() {
        Database databaseTest = new Database();
        databaseTest.addTimeSlot(date1, roomNum1, timeSlot1);
        Set<TimeSlot> timeSlotSet = new HashSet<>();
        timeSlotSet.add(timeSlot1);
        Optional<RoomRecord> actualTimeSlot = databaseTest.deleteRoomRecords(date1, roomNum1, timeSlotSet).stream().findFirst();
        if (actualTimeSlot.isPresent()) {
            assertEquals(timeSlot1, actualTimeSlot.get().getTimeSlot());
        } else {
            fail("fail");
        }
    }

    @Test
    public void verifyBookingID() {
        String bookingID = database.getRoomRecord(date1, roomNum1, timeSlot1).generateBookingID();
        assertEquals(bookingID, database.getRoomRecord(date1, roomNum1, timeSlot1).getBookingID());
    }
}