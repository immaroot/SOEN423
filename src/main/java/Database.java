
import java.util.*;
import java.util.stream.Collectors;

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

        if (dates.containsKey(date) && dates.get(date).containsKey(roomNumber)) {
            for (TimeSlot timeSlot : timeSlotSet) {
                roomRecords.add(dates.get(date).get(roomNumber).remove(timeSlot));
            }
        }
        return roomRecords;
    }

    public int getRoomRecordCount() {
        return dates.keySet().stream().mapToInt(date -> getRooms(date).stream().mapToInt(roomNumber -> getRoomRecords(date, roomNumber).size()).sum()).sum();
    }

    synchronized public int getTimeSlotAvailableCount(Date some_date) {
/*
        --- to Test concurrency ---
        Random rand = new Random();
        try {
            this.wait(rand.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        int count = 0;
        if (!dates.isEmpty() && dates.containsKey(some_date)) for (int roomNumber : dates.get(some_date).keySet()) {
            if (dates.get(some_date).containsKey(roomNumber)) for (RoomRecord record : dates.get(some_date).get(roomNumber).values()) {
                    if (!record.isBooked()) {
                        count++;
                }
            }
        }

        return count;
//        return dates.forEach((date, integerHashMapHashMap)
//                -> integerHashMapHashMap.forEach((integer, timeSlotRoomRecordHashMap)
//                -> timeSlotRoomRecordHashMap.values().stream().filter(roomRecord -> !roomRecord.isBooked()).count()));
    }


//    public static void main(String[] args) {
//        List<String> list = Arrays.asList("A", "B", "C", "D", "B");
//
//        HashMap<String, Integer> hashMap = new HashMap<>();
//
//        HashMap<String, HashMap<String, Integer>> hashMap2 = new HashMap<>();
//
//        for (int i=0; i < list.size(); i++) {
//            hashMap.put(list.get(i), i);
//        }
//
//
//        System.out.println("Filter: " + list.stream().filter(x -> x.equals("B")).count());
//        System.out.println("Predicate: " + hashMap.values().stream().filter(integer -> integer%2 == 0).count());
//
//        hashMap2.put("A", new HashMap<>());
//        hashMap2.get("A").put("A", 3);
//        hashMap2.get("A").put("B", 3);
//        hashMap2.put("B", new HashMap<>());
//        hashMap2.get("B").put("A", 3);
//        hashMap2.get("B").put("B", 3);
//
//        List<Integer> integers = hashMap2.values().stream().map(stringIntegerHashMap -> stringIntegerHashMap.values().stream().filter(integer -> integer % 2 == 0)).flatMap(Collection::iterator)));
//        long num = hashMap2.values().stream().map(stringIntegerHashMap -> stringIntegerHashMap.values().stream().filter(integer -> integer % 2 == 0)).count();
//        System.out.println("count: " + num);
//
//    }
}
