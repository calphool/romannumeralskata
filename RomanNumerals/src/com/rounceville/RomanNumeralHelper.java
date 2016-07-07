package com.rounceville;

import java.text.ParseException;
import java.util.regex.*;

public class RomanNumeralHelper {



	public Object toInt(String inp) throws ParseException {

		int iTotal = 0;
		inp = inp.toUpperCase();
		
		validateRomanNumeralInput(inp);
		
		for(int i=0; i < inp.length(); i++) {
			char c = inp.charAt(i);
			iTotal+= charToInt(c);
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
}
