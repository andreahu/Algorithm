package cscie55.hw5.rentals;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class VideoTest { 
	@Test
	public void testVideo() throws VideoException {
		Video leanIn = new Video("Lean In", 2013);
		Video leanIn2 = new Video("Lean In", 2013);
		Video leanIn3 = new Video("Lean In 3333", 2013);
		assertTrue(leanIn.isAvailable());
		assertFalse(leanIn.isNotAvailable());
		assertTrue(leanIn.equals(leanIn2));
		assertFalse(leanIn.equals(leanIn3));

		String leanInString = leanIn.toString();
		assertEquals("Lean In: 2013 is AVAILABLE", leanInString);

		//test removeFromStock() and replaceToStock()
		leanIn.removeFromStock();
		assertFalse(leanIn.isAvailable());
		leanIn.replaceToStock();
		assertTrue(leanIn.isAvailable());


		//test checkIn() and checkOut()
		leanIn3.checkIn();
		assertTrue(leanIn3.isAvailable());
		leanIn3.checkOut();
		assertTrue(leanIn3.isNotAvailable());

		try{
			leanIn3.checkOut();
			fail();
		}catch(RuntimeException e){
			//expected
		}


		//test compareTo(...)
		assertEquals(0, leanIn.compareTo(leanIn2));
		assertEquals(-5, leanIn.compareTo(leanIn3));

		Video leanIm = new Video("Lean Im", 2013);
		assertEquals(1, leanIn.compareTo(leanIm));

		Account charlie = new Account("Charlie", "X", "charlie@gmail.com");
		try{
			int i = leanIn.compareTo(charlie);
			fail();
		}catch(RuntimeException e){
			//expected
		}

	}
}
