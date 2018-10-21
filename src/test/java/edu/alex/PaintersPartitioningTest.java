package edu.alex;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.alex.PaintersPartitioning;
import org.junit.Test;

public class PaintersPartitioningTest {
	

	private int doTest(int workers, String jobs) {
		return new PaintersPartitioning().compute(workers, Arrays.stream(jobs.split(" ")).mapToInt(Integer::valueOf).toArray());
	}
	
	@Test(timeout=3000)
	public void doTest() {
		assertEquals(60, new PaintersPartitioning().compute(2, new int [] {10, 20, 30, 40}));
		assertEquals(37, new PaintersPartitioning().compute(5, new int [] {15, 1, 21, 3, 7, 2, 11, 9, 1, 20, 7, 10, 1, 16, 9, 9, 1, 3}));
		assertEquals(714, doTest(6, "444 270 225 334 410 433 249 193 487 312"));
		assertEquals(740, doTest(14, "189 107 444 400 84 270 225 334 410 433 249 193 487 312 493 430 422 208 90 245 337 234 168 360"));
		assertEquals(647, doTest(26, "274 465 130 135 254 45 70 122 149 95 453 65 392 331 316 484 372 339 45 46 31 167 351 415 387 275 355 440 290 462 436 416 279 66 403 33 464 473 8 113 420 461 30 312"));
		assertEquals(805, doTest(35, "189 107 444 400 84 270 225 334 410 433 249 193 487 312 493 430 422 208 90 245 337 234 168 360 189 107 444 400 84 270 225 334 410 433 249 193 487 312 493 430 422 208 90 245 337 234 168 360 189 107 444 400 84 270 225 334 410 433 249 193 487 312 493 430 422 208 90 245 337 234 168 360"));
		assertEquals(744, doTest(80, "189 107 444 400 84 270 225 334 410 433 249 193 487 312 493 430 422 208 90 245 337 234 168 360 189 107 444 400 84 270 225 334 410 433 249 193 487 312 493 430 422 208 90 245 337 234 168 360 189 107 444 400 84 270 225 334 410 433 249 193 487 312 493 430 422 208 90 245 337 234 168 360 189 107 444 400 84 270 225 334 410 433 249 193 487 312 493 430 422 208 90 245 337 234 168 360 189 107 444 400 84 270 225 334 410 433 249 193 487 312 493 430 422 208 90 245 337 234 168 360 189 107 444 400 84 270 225 334 410 433 249 193 487 312 493 430 422 208 90 245 337 234 168 360"));
		assertEquals(37, new PaintersPartitioning().compute(5, new int [] {15, 1, 21, 3, 7, 2, 11, 9, 1, 20, 7, 10, 1, 16, 9, 9, 1, 3}));
		assertEquals(37, new PaintersPartitioning().compute(5, new int [] {3, 1, 9, 9, 16, 1, 10, 7, 20, 1, 9, 11, 2, 7, 3, 21, 1, 15}));
		assertEquals(647, new PaintersPartitioning().compute(26, new int [] {274, 465, 130, 135, 254, 45, 70, 122, 149, 95, 453, 65, 392, 331, 316, 484, 372, 339, 45, 46, 31, 167, 351, 415, 387, 275, 355, 440, 290, 462, 436, 416, 279, 66, 403, 33, 464, 473, 8, 113, 420, 461, 30, 312}));
		assertEquals(50, new PaintersPartitioning().compute(25, new int [] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 50, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
		assertEquals(50, new PaintersPartitioning().compute(25, new int [] {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 50, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 4, 3, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1}));
		assertEquals(37, new PaintersPartitioning().compute(5, new int [] {15, 1, 21, 3, 7, 2, 11, 9, 1, 20, 7, 10, 1, 16, 9, 9, 1, 3}));
		assertEquals(37, new PaintersPartitioning().compute(5, new int [] {3, 1, 9, 9, 16, 1, 10, 7, 20, 1, 9, 11, 2, 7, 3, 21, 1, 15}));
	} 
	
}
