package com.rounceville;

public class RomanNumeralHelper {



	public Object toInt(String inp) {
		if(inp.equalsIgnoreCase("M"))
			return new Integer(1000);
		if(inp.equalsIgnoreCase("D"))
			return new Integer(500);
		if(inp.equalsIgnoreCase("C"))
			return new Integer(100);
		if(inp.equalsIgnoreCase("L"))
			return new Integer(50);
		if(inp.equalsIgnoreCase("X"))
			return new Integer(10);
		if(inp.equalsIgnoreCase("V"))
			return new Integer(5);
		if(inp.equalsIgnoreCase("I"))
			return new Integer(1);
		
		return null;
	}

}
