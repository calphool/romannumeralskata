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
		// prove primitives work
		assertEquals(rnh.toArabic("M"), 1000);
		assertEquals(rnh.toArabic("D"), 500);
		assertEquals(rnh.toArabic("C"), 100);
		assertEquals(rnh.toArabic("L"), 50);		
		assertEquals(rnh.toArabic("X"), 10);		
		assertEquals(rnh.toArabic("V"), 5);
		assertEquals(rnh.toArabic("I"), 1);
	}
	
	
	@Test
	public void testTripleRepeats() throws ParseException {
		// prove triples work
		assertEquals(rnh.toArabic("III"),3);
		assertEquals(rnh.toArabic("XXX"),30);
		assertEquals(rnh.toArabic("CCC"),300);
		assertEquals(rnh.toArabic("MMM"),3000);
	}
	
	
	@Test
	public void testQuadrupleIIIIRepeatFails() throws ParseException {
		// No quadruples 
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toArabic("IIII"),4);
	}
	
	
	@Test
	public void testQuadrupleXXXXRepeatFails() throws ParseException {
		// No quadruples 
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toArabic("XXXX"),40);
	}
	
	
	@Test
	public void testQuadrupleCCCCRepeatFails() throws ParseException {
		// No quadruples 
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toArabic("CCCC"),400);
	}
	
	
	@Test
	public void testQuadrupleMMMMRepeatFails() throws ParseException {
		// No quadruples 
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toArabic("MMMM"),4000);
	}

	
	@Test
	public void testQuadrupleMIIIIRepeatFails() throws ParseException {
		// No quadruples 
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toArabic("MIIII"),1004);
	}
	
	
	@Test
	public void testRepeatingVFails() throws ParseException {
		// V's cannot be doubled
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toArabic("VV"),10);
	}

	
	@Test
	public void testRepeatingLFails() throws ParseException {
		// L's cannot be doubled
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toArabic("LL"),100);
	}

	
	@Test
	public void testRepeatingDFails() throws ParseException {
		// D's cannot be doubled
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toArabic("DD"),1000);
	}
	
	
	@Test
	public void testRepeatingMDDFails() throws ParseException {
		// D's cannot be doubled
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toArabic("MDD"),2000);
	}
	
	
	@Test
	public void testIVandIXandXLandXCandCDandCM() throws ParseException {
		// specific valid tests for subtraction
		assertEquals(rnh.toArabic("IV"), 4);
		assertEquals(rnh.toArabic("IX"), 9);
		assertEquals(rnh.toArabic("XC"), 90);
		assertEquals(rnh.toArabic("CD"), 400);
		assertEquals(rnh.toArabic("CM"), 900);
	}

	
	@Test
	public void testILandICandIDandIM() throws ParseException {
		// specific invalid tests (I is too far away from L to subtract for example)
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toArabic("IL"), 49);
		assertNotEquals(rnh.toArabic("IC"), 99);
		assertNotEquals(rnh.toArabic("ID"), 499);
		assertNotEquals(rnh.toArabic("IM"), 999);
	}

	@Test
	public void testNoDoubleSubtractions() throws ParseException {
		// double subtraction isn't allowed
		exception.expect(ParseException.class);
		assertNotEquals(rnh.toArabic("XXC"), 80);
	}

	
	@Test
	public void testGivenFeature1TestCases() throws ParseException {
		// these are required tests for feature 1
		assertEquals(rnh.toArabic("I"), 1);
		assertEquals(rnh.toArabic("III"), 3);
		assertEquals(rnh.toArabic("IX"), 9);
		assertEquals(rnh.toArabic("MLXVI"), 1066);
		assertEquals(rnh.toArabic("MCMLXXXIX"), 1989);
	}
	
	
	@Test
	public void testPrimitiveIntToLetter() {
		// these are primitive tests
		assertEquals(rnh.toRomanNumeral(1000), "M");
		assertEquals(rnh.toRomanNumeral(500), "D");
		assertEquals(rnh.toRomanNumeral(100), "C");
		assertEquals(rnh.toRomanNumeral(50), "L");
		assertEquals(rnh.toRomanNumeral(10), "X");
		assertEquals(rnh.toRomanNumeral(5), "V");
		assertEquals(rnh.toRomanNumeral(1), "I");
	}
	
	
	@Test
	public void testGivenFeature2TestCases() {
		// these were required test cases for feature 2
		assertEquals(rnh.toRomanNumeral(1), "I");
		assertEquals(rnh.toRomanNumeral(3), "III");
		assertEquals(rnh.toRomanNumeral(9), "IX");
		assertEquals(rnh.toRomanNumeral(1066), "MLXVI");
		assertEquals(rnh.toRomanNumeral(1989), "MCMLXXXIX");
	}
	
	
	@Test
	public void testNegativeNumbers() throws IllegalArgumentException {
		// no negative numbers
		exception.expect(IllegalArgumentException.class);
		rnh.toRomanNumeral(-1);
	}
	
	
	@Test 
	public void testZeroNumber() throws IllegalArgumentException {
		// no zeros
		exception.expect(IllegalArgumentException.class);
		rnh.toRomanNumeral(0);
	}
	
	
	@Test
	public void testLargeNumber() throws IllegalArgumentException {
		// no numbers larger than 3999
		exception.expect(IllegalArgumentException.class);
		rnh.toRomanNumeral(4000);
	}

	
	@Test
	public void testLessControversialExhaustiveTest() throws ParseException {
		// list generated from excel... since the range is limited (1-3999), we can 
		// literally test every single value for validity

		String sList = RomanNumeralList.getFullRomanNumeralTable();
		
		String[] sLines = sList.split("\n");
		for(String sLine : sLines) {
			String sKey = sLine.split(":")[0];
			String sValue = sLine.split(":")[1];
			assertEquals(rnh.toRomanNumeral(Integer.parseInt(sKey)), sValue);
			assertEquals(rnh.toArabic(sValue), Integer.parseInt(sKey));
		}
	}
	
	
	@Test
	public void testCrossCheckExhaustively() throws ParseException {
		// run every number through the helper class (arabic int to roman and roman back to arabic int)
		for(int i=1;i<4000;i++) {
			String sNumeral = rnh.toRomanNumeral(i);
			assertEquals(i, rnh.toArabic(sNumeral));
		}
	}
}
