package com.rounceville;

import java.text.ParseException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.*;

public class RomanNumeralHelper {

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

	
	public Object toInt(String inp) throws ParseException {
		int iTotal = 0;
		inp = inp.toUpperCase();
		
		validateRomanNumeralInput(inp);
		
		Queue<AbstractMap.SimpleEntry<Integer,String>> q = getRomanNumeralValueQueue();
	
		while(inp.length() > 0) {                                   // while we still have a string to work with
			SimpleEntry<Integer, String> se = q.poll();				// pull queue item off
			String sNode = se.getValue();							// what letter are we on?
			String sInputWindow = inp.substring(0, Math.min(sNode.length(), inp.length()));  // window the input string
			while(sInputWindow.equalsIgnoreCase(sNode)) {           // while the window still matches the queue letter(s)
				iTotal += se.getKey();								// add this letter(s) to the total
				inp = inp.substring(sNode.length());				// advance the input string forward
				sInputWindow = inp.substring(0, Math.min(sNode.length(), inp.length()));  // determine new window value
			}
		}

		return new Integer(iTotal);
	}
	
	private void validateRomanNumeralInput(String inp) throws ParseException {		
		// http://stackoverflow.com/questions/267399/how-do-you-match-only-valid-roman-numerals-with-a-regular-expression
		if(!Pattern.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$",inp)) 
			throw new ParseException("Unknown problem with your roman numeral input: " + inp, -1);
	}
	
	

	public String toNumeral(int i) {
		StringBuilder sb = new StringBuilder();
		
		Queue<AbstractMap.SimpleEntry<Integer,String>> q = getRomanNumeralValueQueue();
		
		while(!q.isEmpty()) {                              // while we still have letters to check
			SimpleEntry<Integer, String> qItem = q.poll(); // pull the next one off
			while(i >= qItem.getKey()) {                   // while our input value exceeds the value of this letter
				sb.append(qItem.getValue());               // append this letter to our output string
				i = i - qItem.getKey();                    // subtract this letter's value from running decrementer
			}
		}
		
		return sb.toString();
	}
}