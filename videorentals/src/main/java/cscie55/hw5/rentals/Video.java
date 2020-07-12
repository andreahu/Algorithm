package cscie55.hw5.rentals;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Created by charliesawyer on 9/12/16.
 */
public class Video implements Comparable {
    private static int EARLIEST_YEAR = 1900;

    enum AVAILABILITY {AVAILABLE, UNAVAILABLE, OUT_OF_STOCK}

    final private String title;
    final private Integer year;
    private AVAILABILITY availablity;

    //AH: tested constructor in VideoTest
    public Video(String title, Integer year) throws VideoException {
        if (title.isEmpty()) {
            throw new VideoException("Invalid parameter: empty title");
        }
        if (LocalDate.now().getYear() < year || year < EARLIEST_YEAR) {
            throw new VideoException("Invalid year " + year);
        }
        this.title = title;
        this.year = year;
        availablity = AVAILABILITY.AVAILABLE;
    }

    @Override
    //AH: tested below method in VideoTest
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (Video.class != other.getClass()) {
            return false;
        }
        Video otherVideo = (Video) other;
        return title.equals(otherVideo.getTitle()) &&
                year.equals(otherVideo.getYear());
    }

    @Override
    public int hashCode() {
        return title.hashCode() * year;
    }
    //AH: tested below method in VideoTest
    public int compareTo(Object other) {
        if (Video.class != other.getClass()) {
            throw new RuntimeException("illegal type comparison: " +
                    other.getClass().getName());
        }
        // Alphabetic by title
        return title.compareTo(((Video) other).getTitle());
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    //AH: tested below method in VideoTest
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(title)
                .append(": ")
                .append(year)
                .append(" is ")
                .append(availablity);
        return stringBuilder.toString();
    }

    //AH: tested below method in VideoTest
    public boolean isAvailable() {
        return availablity == AVAILABILITY.AVAILABLE;
    }
    //AH: tested below method in VideoTest
    public boolean isNotAvailable() { return availablity == AVAILABILITY.UNAVAILABLE; }

    //AH: tested below method in VideoTest
    public void checkOut() {
        if (isAvailable()) {
            availablity = AVAILABILITY.UNAVAILABLE;
        } else {
            throw new RuntimeException("Video is " + availablity);
        }
    }

    //AH: tested below method in VideoTest
    public void checkIn() {
        availablity = AVAILABILITY.AVAILABLE;
    }
    //AH: tested below method in VideoTest
    public void removeFromStock() {
        availablity = AVAILABILITY.OUT_OF_STOCK;
    }
    //AH: tested below method in VideoTest
    public void replaceToStock() {
        availablity = AVAILABILITY.AVAILABLE;
    }
}
