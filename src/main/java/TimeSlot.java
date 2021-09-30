import java.util.Objects;

public class TimeSlot {

    private final Time start;
    private final Time end;

    public TimeSlot(Time start, Time end) {
        this.start = start;
        this.end = end;
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }

    public boolean overlaps(TimeSlot timeSlot) {
        return (this.start.compareTo(timeSlot.getStart()) >= 0 && this.end.compareTo(timeSlot.getEnd()) <= 0) ||
                (this.start.compareTo(timeSlot.getStart()) >= 0 && this.start.compareTo(timeSlot.getEnd()) < 0) ||
                (this.end.compareTo(timeSlot.getStart()) > 0 && this.end.compareTo(timeSlot.getEnd()) <= 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return this.start == timeSlot.getStart() && this.end == timeSlot.getEnd();
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}