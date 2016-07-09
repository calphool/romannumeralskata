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
	public void testPrimitivesLetterToInt() throws ParseException {
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
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toInt("IL"), 49);
		assertNotEquals(rnh.toInt("IC"), 99);
		assertNotEquals(rnh.toInt("ID"), 499);
		assertNotEquals(rnh.toInt("IM"), 999);
	}

	@Test
	public void testNoDoubleSubtractions() throws ParseException {
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toInt("XXC"), 80);
	}

	@Test
	public void testGivenFeature1TestCases() throws ParseException {
		assertEquals(rnh.toInt("I"), 1);
		assertEquals(rnh.toInt("III"), 3);
		assertEquals(rnh.toInt("IX"), 9);
		assertEquals(rnh.toInt("MLXVI"), 1066);
		assertEquals(rnh.toInt("MCMLXXXIX"), 1989);
	}
	
	@Test
	public void testPrimitiveIntToLetter() {
		assertEquals(rnh.toNumeral(1000), "M");
		assertEquals(rnh.toNumeral(500), "D");
		assertEquals(rnh.toNumeral(100), "C");
		assertEquals(rnh.toNumeral(50), "L");
		assertEquals(rnh.toNumeral(10), "X");
		assertEquals(rnh.toNumeral(5), "V");
		assertEquals(rnh.toNumeral(1), "I");
	}
	
	@Test
	public void testGivenFeature2TestCases() {
		assertEquals(rnh.toNumeral(1), "I");
		assertEquals(rnh.toNumeral(3), "III");
		assertEquals(rnh.toNumeral(9), "IX");
		assertEquals(rnh.toNumeral(1066), "MLXVI");
		assertEquals(rnh.toNumeral(1989), "MCMLXXXIX");
	}
	
	@Test
	public void testNegativeNumbers() throws IllegalArgumentException {
		exception.expect(IllegalArgumentException.class);
		rnh.toNumeral(-1);
	}
	
	@Test 
	public void testZeroNumber() throws IllegalArgumentException {
		exception.expect(IllegalArgumentException.class);
		rnh.toNumeral(0);
	}
	
	@Test
	public void testLargeNumber() throws IllegalArgumentException {
		exception.expect(IllegalArgumentException.class);
		rnh.toNumeral(4000);
	}

	
	@Test
	public void testCrossCheckExhaustively() throws ParseException {
		for(int i=1;i<4000;i++) {
			String sNumeral = rnh.toNumeral(i);
			assertEquals(i, rnh.toInt(sNumeral));
		}
	}
	
	
	
}
