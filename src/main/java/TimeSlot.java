import java.util.Calendar;
import java.util.Objects;

public class TimeSlot {

    private final Calendar start;
    private final Calendar end;

    public TimeSlot(Calendar start, Calendar end) {
        this.start = start;
        this.end = end;
    }

    public TimeSlot(Calendar start, int lengthInMinutes) {
        this.start = start;
        this.end = start;
        this.end.add(Calendar.MINUTE, lengthInMinutes);
    }

    public Calendar getStart() {
        return start;
    }

    public Calendar getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return start.get(Calendar.YEAR) == timeSlot.getStart().get(Calendar.YEAR) && 
                start.get(Calendar.MONTH) == timeSlot.getStart().get(Calendar.MONTH) &&
                start.get(Calendar.DATE) == timeSlot.getStart().get(Calendar.DATE) &&
                start.get(Calendar.MINUTE) == timeSlot.getStart().get(Calendar.MINUTE) &&
                end.get(Calendar.YEAR) == timeSlot.getEnd().get(Calendar.YEAR) &&
                end.get(Calendar.MONTH) == timeSlot.getEnd().get(Calendar.MONTH) &&
                end.get(Calendar.DATE) == timeSlot.getEnd().get(Calendar.DATE) &&
                end.get(Calendar.MINUTE) == timeSlot.getEnd().get(Calendar.MINUTE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}