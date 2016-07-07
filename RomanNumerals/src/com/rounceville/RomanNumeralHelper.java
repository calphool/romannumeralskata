package com.rounceville;

import java.text.ParseException;
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
		if(Pattern.matches(".*IIII.*", inp))
			throw new ParseException("Only 3 I's allowed in a row.",-1);
		if(Pattern.matches(".*XXXX.*", inp))
			throw new ParseException("Only 3 X's allowed in a row.",-1);
		if(Pattern.matches(".*CCCC.*", inp))
			throw new ParseException("Only 3 C's allowed in a row.",-1);
		if(Pattern.matches(".*MMMM.*", inp))
			throw new ParseException("Only 3 M's allowed in a row.",-1);
		if(Pattern.matches(".*VV.*", inp))
			throw new ParseException("Only 1 V allowed in a row.",-1);
		if(Pattern.matches(".*LL.*", inp))
			throw new ParseException("Only 1 L allowed in a row.",-1);
		if(Pattern.matches(".*DD.*", inp))
			throw new ParseException("Only 1 D allowed in a row.",-1);
		
		// this regex was modified from the one located here:
		// http://stackoverflow.com/questions/267399/how-do-you-match-only-valid-roman-numerals-with-a-regular-expression
		if(!Pattern.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$",inp)) 
			throw new ParseException("Unknown problem with your roman numeral input: " + inp, -1);
	}

	private int charToInt(char c) throws ParseException {
		if(c == 'M')
			return 1000;
        if(c == 'D')
        	return 500;
        if(c == 'C')
        	return 100;
        if(c == 'L')
        	return 50;
        if(c == 'X')
        	return 10;
        if(c == 'V')
        	return 5;
        if(c == 'I')
        	return 1;
        
        throw new ParseException("Unknown roman numeral: " + c, -1);
	}
	

	public String toNumeral(int i) {
		StringBuilder sb = new StringBuilder();
		
		while(i >= 1000) {
			sb.append("M");
			i-=1000;
		}
		while(i >= 900) {
		    sb.append("CM");
		    i-=900;
		}
		while(i >= 500) {
			sb.append("D");
			i-=500;
		}
		while(i >= 400) {
			sb.append("CD");
			i-=400;
		}
		while(i >= 100) {
			sb.append("C");
			i-=100;
		}
		while(i >= 90) {
			sb.append("XC");
			i-=90;
		}
		while(i >= 50) {
			sb.append("L");
			i-=50;
		}
		while(i >= 40) {
			sb.append("XL");
			i-=40;
		}
		while(i >= 10) {
			sb.append("X");
			i-=10;
		}
		while(i >= 9) {
			sb.append("IX");
			i-=9;
		}
		while(i >= 5) {
			sb.append("V");
			i-=5;
		}
		while(i >= 4) {
			sb.append("IV");
			i-=4;
		}
		while(i >= 1) {
			sb.append("I");
			i--;
		}
		
		return sb.toString();
	}
}
