package edu.alex;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.alex.PhoneDirectory;
import org.junit.Test;

public class PhoneDictTest {

	@Test
	public void doTest() {
		assertArrayEquals(new Object [] {"geeikistest geeksforgeeks geeksfortest", 
				"geeikistest geeksforgeeks geeksfortest",
				"geeikistest geeksforgeeks geeksfortest",
				"geeikistest",
				"0",
				"0"}, new PhoneDirectory().compute(Arrays.asList("geeikistest", "geeksforgeeks", "geeksfortest"), "geeips").stream().toArray());
		assertArrayEquals(new Object [] {"ab"}, new PhoneDirectory().compute(Arrays.asList("ab cb f".split(" ")), "a").stream().toArray());
	} 
}
