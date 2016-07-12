package com.rounceville;

import java.text.ParseException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class RomanNumeralHelper {

	/**
	 * Returns a queue containing Arabic integers and their associated Roman
	 * Numeral primitives. 
	 *
	 * @return      Queue of entries
	 */
	private Queue<AbstractMap.SimpleEntry<Integer,String>> getRomanNumeralValueQueue() {
		Queue<AbstractMap.SimpleEntry<Integer,String>> q = new LinkedList<AbstractMap.SimpleEntry<Integer,String>>();
		q.add(new SimpleEntry<Integer, String>(1000,"M"));
		q.add(new SimpleEntry<Integer, String>(900,"CM"));
		q.add(new SimpleEntry<Integer, String>(500,"D"));
		q.add(new SimpleEntry<Integer, String>(400,"CD"));
		q.add(new SimpleEntry<Integer, String>(100,"C"));
		q.add(new SimpleEntry<Integer, String>(90,"XC"));
		q.add(new SimpleEntry<Integer, String>(50,"L"));
		q.add(new SimpleEntry<Integer, String>(40,"XL"));
		q.add(new SimpleEntry<Integer, String>(10,"X"));
		q.add(new SimpleEntry<Integer, String>(9,"IX"));
		q.add(new SimpleEntry<Integer, String>(5,"V"));
		q.add(new SimpleEntry<Integer, String>(4,"IV"));
		q.add(new SimpleEntry<Integer, String>(1,"I"));

		return q;
	}

	/**
	 * Convert a Roman Numeral string to an Integer
	 *
	 * @return      An Integer object
	 */	
	public Object toArabic(String sRomanInput) throws ParseException {
		int iReturnValue = 0;
		sRomanInput = sRomanInput.toUpperCase();
		
		validateRomanNumeralInput(sRomanInput);
		
		Queue<AbstractMap.SimpleEntry<Integer,String>> qRomanAndArabic = getRomanNumeralValueQueue();
	
		//TODO: find a better way to do this if I have enough time...
		//      This works, but I'm using the wrong datatype for sRomanInput at this point
		//      I'm "abusing" a string by treating it like a queue and pulling character nodes 
		//      from the front of it.  It would be better represented as a true queue chopped up into
		//      primitives for nodes (the trick seems to be figuring out the primitive sizes, since they
		//      vary based on location -- just need to dive in and try something, and then refactor 
		//      with time -- maybe make an "internal" representation of Roman primitives that uses 
		//      single characters for every value (for example IX would become ! or something), 
		//      then build an internal-to-external conversion both directions?
		
		while(sRomanInput.length() > 0) {                                   	// while we still have a string to work with
			SimpleEntry<Integer, String> qEntry = qRomanAndArabic.poll();		// pull queue item off
			String sNode = qEntry.getValue();									// what letter are we on?
			String sInputWindow = sRomanInput.substring(0, Math.min(sNode.length(), sRomanInput.length()));  // window the input string
			while(sInputWindow.equalsIgnoreCase(sNode)) {           			// while the window still matches the queue letter(s)
				iReturnValue += qEntry.getKey();								// add this letter(s) to the total
				sRomanInput = sRomanInput.substring(sNode.length());			// advance the input string forward
				sInputWindow = sRomanInput.substring(0, Math.min(sNode.length(), sRomanInput.length()));  // determine new window value
			}
		}

		return new Integer(iReturnValue);
	}

	/**
	 * Throw an exception if the string passed isn't a valid Roman Numeral
	 *
	 * @return      void
	 */	
	private void validateRomanNumeralInput(String sStringToTest) throws ParseException {
		// originally started building up a list of regular expressions here...
		// decided it would be better to find a comprehensive one
		
		// Found:
		// http://stackoverflow.com/questions/267399/how-do-you-match-only-valid-roman-numerals-with-a-regular-expression
		/*
		 * This regular expression translated into English:
		 * starting at the beginning, zero to three M's followed by a choice of
		 * CM, CD, D or (zero to 3 Cs) followed by XC, XL, L, or (zero to 3 Xs)
		 * followed by IX, IV, V, or zero to 3 I's, and that's it
		 */
		
		if(!Pattern.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$",sStringToTest))   
			throw new ParseException("Unknown problem with your roman numeral input: " + sStringToTest, -1);
	}
	
	
	/**
	 * Convert an int to a Roman Numeral string
	 *
	 * @return      String
	 */	
	public String toRomanNumeral(int iArabicNumber) {
		if(iArabicNumber < 1 || iArabicNumber > 3999)
			throw new IllegalArgumentException("Values must be in the range 1 - 3999 inclusive.");
		
		StringBuilder sReturnStringBuilder = new StringBuilder();
		
		Queue<AbstractMap.SimpleEntry<Integer,String>> qRomanAndArabic = getRomanNumeralValueQueue();
		
		while(!qRomanAndArabic.isEmpty()) {                                     // while we still have letters to check
			SimpleEntry<Integer, String> qEntry = qRomanAndArabic.poll();       // pull the next one off
			while(iArabicNumber >= qEntry.getKey()) {                          	// while our input value exceeds the value of this letter
				sReturnStringBuilder.append(qEntry.getValue());    				// append this letter to our output string
				iArabicNumber = iArabicNumber - qEntry.getKey();     			// subtract this letter's value from running decrementer
			}
		}
		
		return sReturnStringBuilder.toString();
	}
}