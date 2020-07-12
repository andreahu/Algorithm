package cscie55.hw5.rentals;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class VideoRentalTest {
    @Test
    public void testRentals() throws VideoException {
        Account andrea = new Account("Andrea", "Hu", "andrea@gmail.com");
        Account charlie = new Account("Charlie", "X", "charlie@gmail.com");
        Video leanIn = new Video("Lean In", 2013);
        Video brainRules = new Video("brainRules", 2014);
        Video chairMan = new Video("chairMan", 2015);
        LocalDate nextWeek = LocalDate.of(2018, 11, 16);
        VideoRental andreaLeanInRental = new VideoRental(leanIn, andrea);
        VideoRental andreaBrainRulesRental = new VideoRental(brainRules, andrea, nextWeek);
        VideoRental charlieChairManRental = new VideoRental(chairMan, charlie);

        //test constructors in a simple way
        assertFalse(leanIn.isAvailable());
        assertTrue(brainRules.isNotAvailable());

        //test equals(..)
        Video leanInDuplicate = new Video("Lean In", 2013);
        VideoRental andreaLeanInRentalDuplicate = new VideoRental(leanInDuplicate, andrea);
        assertTrue(andreaLeanInRental.equals(andreaLeanInRentalDuplicate));

        //test toString() with default due day and customized due day respectively
        String andreaLeanInRentalString = "Lean In->andrea@gmail.com due on "+LocalDate.now().plusDays(VideoRental.getRentalPeriod());
        assertEquals(andreaLeanInRentalString, andreaLeanInRental.toString());
        String andreaBrainRulesRentalString = "brainRules->andrea@gmail.com due on " + andreaBrainRulesRental.getDateDue();
        assertEquals(andreaBrainRulesRentalString, andreaBrainRulesRental.toString());

        //test isOpen() and rentalReturn()
        assertTrue(andreaLeanInRental.isOpen());
        andreaLeanInRental.rentalReturn();
        assertTrue(leanIn.isAvailable());
        assertFalse(andreaLeanInRental.isOpen());

        //test isOverDue()
        //I follow the logic in the isOverDue method to test. However the logic in the method is INCORRECT.
        //I propose to change the code to: return dueDate.isBefore(LocalDate.now());
        assertTrue(andreaBrainRulesRental.isOverDue());


    }
}
