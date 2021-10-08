
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Database {
    private final HashMap<Date, HashMap<Integer, HashMap<TimeSlot, RoomRecord>>> dates;

    public Database() {
        dates = new HashMap<>();
    }

    public void addDate(Date date) {
        if (!dates.containsKey(date)) {
            dates.put(date, new HashMap<>());
        }
    }

    public void addRoom(Date date, int roomNumber) {
        if (!dates.containsKey(date)) {
            addDate(date);
        }
        if (!dates.get(date).containsKey(roomNumber)) {
            dates.get(date).put(roomNumber, new HashMap<>());
        }
    }

    public void addTimeSlot(Date date, int roomNumber, TimeSlot timeSlot) {
        if (!dates.containsKey(date)) {
            addDate(date);
        }
        if (!dates.get(date).containsKey(roomNumber)) {
            addRoom(date, roomNumber);
        }
        if (!dates.get(date).get(roomNumber).containsKey(timeSlot) && !overlapsTimeSlots(date, roomNumber, timeSlot)) {
            dates.get(date).get(roomNumber).put(timeSlot, new RoomRecord(date, roomNumber, timeSlot));
        }
    }

    private boolean overlapsTimeSlots(Date date, int roomNumber, TimeSlot timeSlot) {
        return dates.get(date).get(roomNumber).entrySet().stream().anyMatch(timeSlotRoomRecordEntry -> timeSlot.overlaps(timeSlotRoomRecordEntry.getKey()));
    }

    public Set<Date> getDates() {
        return dates.keySet();
    }

    public Set<Integer> getRooms(Date date) {
        return dates.get(date).keySet();
    }

    public Set<TimeSlot> getTimeSlots(Date date, int roomNumber) {
        return dates.get(date).get(roomNumber).keySet();
    }

    public Collection<RoomRecord> getRoomRecords(Date date, int roomNumber) {
        return dates.get(date).get(roomNumber).values();
    }

    public Collection<RoomRecord> deleteRoomRecords(Date date, int roomNumber, Set<TimeSlot> timeSlotSet) {
        ArrayList<RoomRecord> roomRecords = new ArrayList<>();
        for (TimeSlot timeSlot : timeSlotSet) {
            roomRecords.add(dates.get(date).get(roomNumber).remove(timeSlot));
        }
        return roomRecords;
    }

    public int getRoomRecordCount() {
        return dates.keySet().stream().mapToInt(date -> getRooms(date).stream().mapToInt(roomNumber -> getRoomRecords(date, roomNumber).size()).sum()).sum();
    }
}
