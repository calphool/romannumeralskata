package com.rounceville;

import java.text.ParseException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.*;

public class RomanNumeralHelper {



	public Object toInt(String inp) throws ParseException {
		int iTotal = 0;
		inp = inp.toUpperCase();
		
		validateRomanNumeralInput(inp);
		
		for(int i=0; i < inp.length(); i++) {
			int cVal = charToInt(inp.charAt(i));
			if(i < inp.length() - 1) {
				int cVal2 = charToInt(inp.charAt(i+1));
				boolean bDoSubtraction = true;
				if(cVal == 5 || cVal == 50 || cVal == 500)
					bDoSubtraction = false;
				if(cVal == 1 && cVal2 > 10)
					bDoSubtraction = false;
				if(cVal == 5 && cVal2 > 50)
					bDoSubtraction = false;
				if(bDoSubtraction && (cVal2 > cVal)) {
					iTotal += cVal2;
					cVal = -cVal;
					i++;
				}				
			}
			
			iTotal += cVal;
		}
		
		return new Integer(iTotal);
	}
	
	private void validateRomanNumeralInput(String inp) throws ParseException {		
		// http://stackoverflow.com/questions/267399/how-do-you-match-only-valid-roman-numerals-with-a-regular-expression
		if(!Pattern.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$",inp)) 
			throw new ParseException("Unknown problem with your roman numeral input: " + inp, -1);
	}

	private int charToInt(char c) throws ParseException {
		switch(c) {
			case 'M': return 1000;
			case 'D': return 500;
			case 'C': return 100;
			case 'L': return 50;
			case 'X': return 10;
			case 'V': return 5;
			case 'I': return 1;
			default: throw new ParseException("Unknown roman numeral: " + c, -1); 
		}
	}
	

	public String toNumeral(int i) {
		StringBuilder sb = new StringBuilder();
		
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

		while(!q.isEmpty()) {
			SimpleEntry<Integer, String> qItem = q.poll();
			while(i >= qItem.getKey()) {
				sb.append(qItem.getValue());
				i = i - qItem.getKey();
			}
		}
		
		return sb.toString();
	}
}
