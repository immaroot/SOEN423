import java.io.Serializable;

public class Time implements Comparable<Time>, Serializable {

    int hour;
    int minutes;

    public Time(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    @Override
    public int compareTo(Time time) {
        if (this.hour == time.hour && this.minutes == time.minutes) {
            return 0;
        } else if (this.hour == time.hour) {
            if (this.minutes > time.minutes) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.hour > time.hour) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Time time = (Time) obj;
        return this.hour == time.hour && this.minutes == time.minutes;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return hour + ":" + minutes;
    }
}
