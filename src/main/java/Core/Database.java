package Core;

import java.util.*;

public class Database {
    private final String campusName;
    private final HashMap<Date, HashMap<Integer, HashMap<TimeSlot, RoomRecord>>> dates;

    public Database(String campusName) {
        this.campusName = campusName;
        dates = new HashMap<>();
    }

    synchronized public void addDate(Date date) {
        if (!dates.containsKey(date)) {
            dates.put(date, new HashMap<>());
        }
    }

    synchronized public void addRoom(Date date, int roomNumber) {
        if (!dates.containsKey(date)) {
            addDate(date);
        }
        if (!dates.get(date).containsKey(roomNumber)) {
            dates.get(date).put(roomNumber, new HashMap<>());
        }
    }

    synchronized public void addTimeSlot(Date date, int roomNumber, TimeSlot timeSlot) {
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

    synchronized private boolean overlapsTimeSlots(Date date, int roomNumber, TimeSlot timeSlot) {
        return dates.get(date).get(roomNumber).entrySet().stream().anyMatch(timeSlotRoomRecordEntry -> timeSlot.overlaps(timeSlotRoomRecordEntry.getKey()));
    }

    synchronized public Set<Date> getDates() {
        return dates.keySet();
    }

    synchronized public Set<Integer> getRooms(Date date) {
        return dates.get(date).keySet();
    }

    synchronized public Set<TimeSlot> getTimeSlots(Date date, int roomNumber) {
        return dates.get(date).get(roomNumber).keySet();
    }

    synchronized public Collection<RoomRecord> getRoomRecords(Date date, int roomNumber) {
        return dates.get(date).get(roomNumber).values();
    }

    synchronized public Collection<RoomRecord> deleteRoomRecords(Date date, int roomNumber, Set<TimeSlot> timeSlotSet) {
        ArrayList<RoomRecord> roomRecords = new ArrayList<>();

        if (dates.containsKey(date) && dates.get(date).containsKey(roomNumber)) {
            for (TimeSlot timeSlot : timeSlotSet) {
                roomRecords.add(dates.get(date).get(roomNumber).remove(timeSlot));
                dates.get(date).get(roomNumber).remove(timeSlot);
            }
        }
        return roomRecords;
    }

    synchronized public int getRoomRecordCount() {
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

    synchronized public boolean hasDate(Date date) {
        return dates.containsKey(date);
    }

    synchronized public boolean hasRoom(Date date, int roomNumber) {
        return hasDate(date) && (dates.get(date).containsKey(roomNumber));
    }

    synchronized public boolean hasTimeSlot(Date date, int roomNumber, TimeSlot timeSlot) {
        return hasRoom(date, roomNumber) && (dates.get(date).get(roomNumber).containsKey(timeSlot));
    }

    synchronized public boolean isBooked(Date date, int roomNumber, TimeSlot timeSlot) {
        return hasTimeSlot(date, roomNumber, timeSlot) && (dates.get(date).get(roomNumber).get(timeSlot).isBooked());
    }

    synchronized public String bookTimeSlot(Date date, int roomNumber, TimeSlot timeSlot, String id) {
        if (!isBooked(date, roomNumber, timeSlot)) {
            RoomRecord record = dates.get(date).get(roomNumber).get(timeSlot);
            if (record == null) {
                return null;
            }
            record.setBookedBy(id);
            record.generateBookingID(campusName);
            return record.getBookingID();
        } else {
            return null;
        }
    }

    synchronized public String getCampusName() {
        return campusName;
    }

    synchronized public RoomRecord getRoomRecordByBookingID(String bookingID) {
        return dates.values().stream()
                .flatMap(integerHashMapHashMap -> integerHashMapHashMap.values().stream()
                    .flatMap(timeSlotRoomRecordHashMap -> timeSlotRoomRecordHashMap.values().stream()))
                .filter(roomRecord -> roomRecord.getBookingID().equals(bookingID)).findFirst().orElse(null);
    }

    synchronized public boolean hasBookingID(String bookingID) {
        return getRoomRecordByBookingID(bookingID) != null;
    }


    synchronized public RoomRecord getRoomRecord(Date date, int roomNumber, TimeSlot timeSlot) {
        if (hasTimeSlot(date, roomNumber, timeSlot)) {
            return dates.get(date).get(roomNumber).get(timeSlot);
        } else {
            return null;
        }
    }
}
