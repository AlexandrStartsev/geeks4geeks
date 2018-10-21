package edu.alex;

import static org.junit.Assert.assertEquals;

import org.alex.SumDiffBits;
import org.junit.Test;

public class SumDiffBitsTest {

	@Test
	public void test() {
		System.out.println(this.getClass().getClassLoader());
		
		SumDiffBits logic = new SumDiffBits();
		
		assertEquals(60, logic.calc(new int[] {1, -1}));
		assertEquals(8, logic.calc(new int[] {1, 3, 5}));
		assertEquals(20, logic.calc(new int[] {7, 3, 11, 6}));
		assertEquals(2942, logic.calc(new int[] {
				7, 3, 11, 6, 2398, 324, 2345, 236, 32566, 99992174, 0,
				233, 8743, 12564, 8342756, 2344, 55345, 345, 34, 676}));
		
	}

}
