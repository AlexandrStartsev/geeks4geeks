package edu.alex;

import static org.junit.Assert.assertEquals;

import org.alex.ClosestPalindrom;
import org.junit.Test;

public class ClosestPalindromTest {

	@Test
	public void test() {
		ClosestPalindrom logic = new ClosestPalindrom();
		int []prem1 = {4, 5, 1, 0, 0, 0}, prem2 = {4, 5, 1, 6, 0, 0, 0};
		
		assertEquals(Long.valueOf(451154), logic.complete(prem1, 3));
		assertEquals(Long.valueOf(4516154), logic.complete(prem2, 4));
		
		assertEquals("99", logic.compute("100"));
		assertEquals("999", logic.compute("1000"));
		assertEquals("9", logic.compute("9"));
		assertEquals("484", logic.compute("489"));
		assertEquals("4235445324", logic.compute("4235436784"));
		assertEquals("4235436776345324", logic.compute("4235436784645275"));
		assertEquals("202", logic.compute("199"));
		assertEquals("898", logic.compute("901"));
		assertEquals("8998", logic.compute("9001"));
		assertEquals("0", logic.compute("0"));
		assertEquals("1", logic.compute("1"));
		assertEquals("9", logic.compute("9"));
		assertEquals("11", logic.compute("11"));
	}

}
