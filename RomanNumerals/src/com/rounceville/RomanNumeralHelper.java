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
		if(i == 1000)
			return "M";
		if(i == 500)
			return "D";
		if(i == 100)
			return "C";
		if(i == 50)
			return "L";
		if(i == 10)
			return "X";
		if(i == 5)
			return "V";
		if(i == 1)
			return "I";
		
		return null;
	}
}
