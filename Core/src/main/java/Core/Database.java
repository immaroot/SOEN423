package Core;

import java.util.*;

public class Database {

    private final Campus campus;
    private final HashMap<Date, HashMap<Integer, HashMap<TimeSlot, RoomRecord>>> dates;

    public Database(Campus campus) {
        this.campus = campus;
        dates = new HashMap<>();
    }

    public String deleteRoom(Date date, int roomNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("Attempting to delete all timeslots for room ").append(roomNumber).append(": \n");
        if (dates.containsKey(date)) {
            dates.get(date).remove(roomNumber);
            sb.append("successfully deleted all room records for the ").append(date);
        }
        return sb.toString();
    }

    public String deleteRoomRecords(Date date, int roomNumber, Set<TimeSlot> timeSlotSet) throws BookingException {
        StringBuilder sb = new StringBuilder();
        sb.append("Attempting to delete timeslots for room ").append(roomNumber).append(": \n");
        if (dates.containsKey(date) && dates.get(date).containsKey(roomNumber)) {
            for (TimeSlot timeSlot : timeSlotSet) {
                if (dates.get(date).get(roomNumber).containsKey(timeSlot)) {
                    sb.append("timeslot: ").append(dates.get(date).get(roomNumber).remove(timeSlot)).append(" deleted\n");
                } else {
                    sb.append("No timeslot ").append(timeSlot).append(" for room ").append(roomNumber).append(".\n");
                    throw new BookingException("Wrong timeslot for room. room: " + roomNumber + " timeslot: " + timeSlot);
                }
            }
        }
        return sb.toString();
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

    public int getTimeSlotAvailableCount(Date some_date) {
        int count = 0;
        if (!dates.isEmpty() && dates.containsKey(some_date)) for (int roomNumber : dates.get(some_date).keySet()) {
            if (dates.get(some_date).containsKey(roomNumber))
                for (RoomRecord record : dates.get(some_date).get(roomNumber).values()) {
                    if (!record.isBooked()) {
                        count++;
                    }
                }
        }
        return count;
    }

    public void cancelBooking(String bookingID) throws BookingException {
        RoomRecord record = dates.values().stream()
                .flatMap(integerHashMapHashMap -> integerHashMapHashMap.values().stream()
                        .flatMap(timeSlotRoomRecordHashMap -> timeSlotRoomRecordHashMap.values().stream()))
                .filter(roomRecord -> roomRecord.getBookingID().equals(bookingID)).findFirst().orElse(null);
        if (record != null) {
            record.cancelReservation(bookingID);
        } else {
            throw new BookingException("There is no bookingID matching what was provided.");
        }
    }

    public String makeBooking(Date date, int roomNumber, TimeSlot timeSlot, String studentID) throws BookingException {
        if (hasTimeSlot(date, roomNumber, timeSlot)) {
            return dates.get(date).get(roomNumber).get(timeSlot).bookRoom(campus.name(), studentID);
        } else {
            throw new BookingException("There are no timeslots matching what was provided.");
        }
    }

    private void addDate(Date date) {
        if (!dates.containsKey(date)) {
            dates.put(date, new HashMap<>());
        }
    }

    private void addRoom(Date date, int roomNumber) {
        if (!dates.containsKey(date)) {
            addDate(date);
        }
        if (!dates.get(date).containsKey(roomNumber)) {
            dates.get(date).put(roomNumber, new HashMap<>());
        }
    }

    private boolean overlapsTimeSlots(Date date, int roomNumber, TimeSlot timeSlot) {
        return dates.get(date).get(roomNumber).entrySet().stream().anyMatch(timeSlotRoomRecordEntry -> timeSlot.overlaps(timeSlotRoomRecordEntry.getKey()));
    }

    private boolean hasDate(Date date) {
        return dates.containsKey(date);
    }

    private boolean hasRoom(Date date, int roomNumber) {
        return hasDate(date) && (dates.get(date).containsKey(roomNumber));
    }

    private boolean hasTimeSlot(Date date, int roomNumber, TimeSlot timeSlot) {
        return hasRoom(date, roomNumber) && (dates.get(date).get(roomNumber).containsKey(timeSlot));
    }


}
