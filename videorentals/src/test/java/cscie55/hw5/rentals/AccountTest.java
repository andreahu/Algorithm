package cscie55.hw5.rentals;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AccountTest {
    @Test
    public void testAccount() {
    }

    @Test
    public void testToString() {
        Account a = new Account("Andrea", "Hu", "test@gmail.com");
        assertEquals("Andrea Hu test@gmail.com", a.toString());
    }

    @Test
    public void testEquals() {
        Account a = new Account("Andrea", "Hu", "test@gmail.com");
        Account b = new Account("Andrea", "Hu", "test@gmail.com");
        assertTrue(a.equals(b));
//		assertEquals(true,a.equals(b)); //This line could replace the line above

    }

    @Test
    public void testOpenAndClosedRentals() throws VideoException {//I add throws here because the constructor of video/videoRental throws exception
        Account andrea = new Account("Andrea", "Hu", "test@gmail.com");
        assertEquals(0, andrea.getNumberOpenRentals());

        Video leanIn = new Video("Lean In", 2013);
        VideoRental andreaLeanInRental = new VideoRental(leanIn, andrea);
        andrea.addRental(andreaLeanInRental);
        assertEquals(1, andrea.getNumberOpenRentals());
        assertEquals(true, andrea.getOpenRentals().contains(andreaLeanInRental));
        assertEquals(true, andrea.hasOpenRental("Lean In"));

        Video brainRules = new Video("brainRules", 2014);
        VideoRental andreaBrainRulesRental = new VideoRental(brainRules, andrea);
        andrea.addRental(andreaBrainRulesRental);

        Video chairMan = new Video("chairMan", 2015);
        VideoRental andreaChairManRental = new VideoRental(chairMan, andrea);
        andrea.addRental(andreaBrainRulesRental);
        assertEquals(3, andrea.getNumberOpenRentals());

        andrea.settleRental("chairMan");
        assertEquals(2, andrea.getNumberOpenRentals());
        assertEquals(1, andrea.getNumberClosedRentals());

        andrea.settleRental(andreaBrainRulesRental);
        assertEquals(1, andrea.getNumberOpenRentals());
        assertEquals(2, andrea.getNumberClosedRentals());

        andrea.settleRentals();
        assertEquals(0, andrea.getNumberOpenRentals());
        assertEquals(3, andrea.getNumberClosedRentals());

        andrea.clearHistory();
        assertEquals(0, andrea.getNumberClosedRentals());

    }



}
