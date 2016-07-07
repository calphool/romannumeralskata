package com.rounceville;

import static org.junit.Assert.*;
import org.junit.Test;

public class RomanNumeralsTest {
	
	@Test
	public void testPrimitivesLetterToInt() {
		RomanNumeralHelper rnh = new RomanNumeralHelper();
		assertEquals(rnh.toInt("M"), 1000);
		assertEquals(rnh.toInt("D"), 500);
		assertEquals(rnh.toInt("C"), 100);
		assertEquals(rnh.toInt("L"), 50);		
		assertEquals(rnh.toInt("X"), 10);		
		assertEquals(rnh.toInt("V"), 5);
		assertEquals(rnh.toInt("I"), 1);
	}

}
