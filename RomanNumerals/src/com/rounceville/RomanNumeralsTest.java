package com.rounceville;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RomanNumeralsTest {

	RomanNumeralHelper rnh;

	@Before
	public void BeforeTests() {
	    rnh = new RomanNumeralHelper();
	}
	
	@Test
	public void testPrimitivesLetterToInt() {
		assertEquals(rnh.toInt("M"), 1000);
		assertEquals(rnh.toInt("D"), 500);
		assertEquals(rnh.toInt("C"), 100);
		assertEquals(rnh.toInt("L"), 50);		
		assertEquals(rnh.toInt("X"), 10);		
		assertEquals(rnh.toInt("V"), 5);
		assertEquals(rnh.toInt("I"), 1);
	}
	
	

}
