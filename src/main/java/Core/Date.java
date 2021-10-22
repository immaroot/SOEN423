package Core;

import java.io.Serializable;
import java.util.Objects;

public class Date implements Comparable<Date>, Serializable {

    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }


    @Override
    public int compareTo(Date date) {
        if (this.year == date.year && this.month == date.month && this.day == date.day) {
            return 0;
        } else if (this.year == date.year) {
            if (this.month == date.month) {
                if (this.day > date.day) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (this.month > date.month) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.year > date.year) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Date date = (Date) obj;
        return this.year == date.year && this.month == date.month && this.day == date.day;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    @Override
    public String toString() {
        return String.format("%02d-%02d-%04d", day, month, year);
    }
}
