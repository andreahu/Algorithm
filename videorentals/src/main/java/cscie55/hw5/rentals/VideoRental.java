package cscie55.hw5.rentals;

import java.time.LocalDate;

/**
 * Created by charliesawyer on 9/12/16.
 */
public class VideoRental {
    private static final int DEFAULT_RENTAL_PERIOD = 14;
    private static int RENTAL_PERIOD_DAYS = 14;
    private final Video video;
    private final Account account;
    private LocalDate dueDate;
    private LocalDate dateReturned = null;

    //AH tested constructor in VideoRentalTest
    public VideoRental(Video video, Account account) throws VideoException {
        if (!video.isAvailable()) {
            throw new VideoException (video.toString());
        }
        video.checkOut();
        this.video = video;
        this.account = account;
        dueDate = LocalDate.now().plusDays(RENTAL_PERIOD_DAYS);
        account.addRental(this);
    }
    //AH tested constructor in VideoRentalTest
    public VideoRental(Video video, Account account, LocalDate dueDate)
            throws VideoException{
        this(video, account);
        this.dueDate = dueDate;
    }
    @Override
    //AH tested the below method in VideoRentalTest
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        VideoRental otherRental = (VideoRental) other;
        return video.equals(otherRental.video) &&
                account == otherRental.getAccount() &&
                dueDate.equals(otherRental.dueDate);
    }
    @Override
    public int hashCode() {
        return video.hashCode()    *
                account.hashCode() *
                dueDate.hashCode();
    }
    @Override
    //AH tested the below method in VideoRentalTest
    public String toString() {
        return video.getTitle() +
                "->" +
                account.getEmail() +
                " due on " +
                dueDate;
    }
    public LocalDate getDateDue() {
        return dueDate;
    }
    public Video getVideo() {
        return video;
    }
    public Account getAccount() {
        return account;
    }
    public boolean isOpen() { return dateReturned == null; }//AH tested the method in VideoRentalTest

    //AH tested the below method in VideoRentalTest
    public void rentalReturn() {
        dateReturned = LocalDate.now();
        video.checkIn();
    }
    public static int getRentalPeriod() { return RENTAL_PERIOD_DAYS; }//AH tested the method in VideoRentalTest

    //AH tested the below method in VideoRentalTest
    public boolean isOverDue() {
        return dueDate.isAfter(LocalDate.now());
//        The logic in this method is incorrect. Below is my proposal of change:
//        return dueDate.isBefore(LocalDate.now());
    }
}
