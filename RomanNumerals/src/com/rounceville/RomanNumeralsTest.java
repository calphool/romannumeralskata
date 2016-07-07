package com.rounceville;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RomanNumeralsTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();	
	
	RomanNumeralHelper rnh;

	@Before
	public void BeforeTests() {
	    rnh = new RomanNumeralHelper();
	}
	
	@Test
	public void testPrimitivesLetterToInt() throws Exception {
		assertEquals(rnh.toInt("M"), 1000);
		assertEquals(rnh.toInt("D"), 500);
		assertEquals(rnh.toInt("C"), 100);
		assertEquals(rnh.toInt("L"), 50);		
		assertEquals(rnh.toInt("X"), 10);		
		assertEquals(rnh.toInt("V"), 5);
		assertEquals(rnh.toInt("I"), 1);
	}
	
	@Test
	public void testTripleRepeats() throws ParseException {
		assertEquals(rnh.toInt("III"),3);
		assertEquals(rnh.toInt("XXX"),30);
		assertEquals(rnh.toInt("CCC"),300);
		assertEquals(rnh.toInt("MMM"),3000);
	}
	
	@Test
	public void testQuadrupleIIIIRepeatFails() throws ParseException {
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toInt("IIII"),4);
	}
	
	@Test
	public void testQuadrupleXXXXRepeatFails() throws ParseException {
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toInt("XXXX"),40);
	}
	
	@Test
	public void testQuadrupleCCCCRepeatFails() throws ParseException {
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toInt("CCCC"),400);
	}
	
	@Test
	public void testQuadrupleMMMMRepeatFails() throws ParseException {
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toInt("MMMM"),4000);
	}

	@Test
	public void testQuadrupleMIIIIRepeatFails() throws ParseException {
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toInt("MIIII"),1004);
	}
	
	@Test
	public void testRepeatingVFails() throws ParseException {
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toInt("VV"),10);
	}

	@Test
	public void testRepeatingLFails() throws ParseException {
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toInt("LL"),100);
	}

	@Test
	public void testRepeatingDFails() throws ParseException {
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toInt("DD"),1000);
	}
	
	@Test
	public void testRepeatingMDDFails() throws ParseException {
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toInt("MDD"),2000);
	}
	
	@Test
	public void testIVandIXandXLandXCandCDandCM() throws ParseException {
		assertEquals(rnh.toInt("IV"), 4);
		assertEquals(rnh.toInt("IX"), 9);
		assertEquals(rnh.toInt("XC"), 90);
		assertEquals(rnh.toInt("CD"), 400);
		assertEquals(rnh.toInt("CM"), 900);
	}

	@Test
	public void testILandICandIDandIM() throws ParseException {
		assertNotEquals(rnh.toInt("IL"), 49);
		assertNotEquals(rnh.toInt("IC"), 99);
		assertNotEquals(rnh.toInt("ID"), 499);
		assertNotEquals(rnh.toInt("IM"), 999);
	}

	@Test
	public void testNoDoubleSubtractions() throws ParseException {
		assertNotEquals(rnh.toInt("XXC"), 80);
	}

}
