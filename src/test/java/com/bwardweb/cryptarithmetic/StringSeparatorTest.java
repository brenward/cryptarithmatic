package com.bwardweb.cryptarithmetic;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.After;
import org.junit.Test;

public class StringSeparatorTest {
	
	StringSeparator separator;
	
	@After
	public void clearDown(){
		separator = null;
	}
	
	@Test(expected = InvalidParameterException.class)
	public void stringSeparatorConstructor_inputNull(){
		separator = new StringSeparator(null);
	}
	
	@Test(expected = InvalidParameterException.class)
	public void stringSeparatorConstructor_inputEmpty(){
		separator = new StringSeparator("");
	}
	
	@Test(expected = InvalidParameterException.class)
	public void setRightHandSide_inputHasNoEquals(){
		separator = new StringSeparator("Test");
	}
	
	@Test(expected = InvalidParameterException.class)
	public void setRightHandSide_inputHasMultipleEquals(){
		separator = new StringSeparator("Test == Test == Test");
	}
	
	@Test
	public void setRightHandSide_validInputOutputNotNull(){
		separator = new StringSeparator("LHS == RHS");
		assertNotNull(separator.getRightHandSide());
	}
	
	@Test
	public void setRightHandSide_validInputOutputCorrect(){
		separator = new StringSeparator("LHS == RHS");
		assertTrue(separator.getRightHandSide().equals("RHS"));
	}
	
	@Test(expected = InvalidParameterException.class)
	public void removeRightHandSide_nullInput(){
		String lhs = StringSeparator.removeRightHandSide(null);	
	}
	
	@Test(expected = InvalidParameterException.class)
	public void removeRightHandSide_emptyInput(){
		String lhs = StringSeparator.removeRightHandSide("");	
	}
	
	@Test(expected = InvalidParameterException.class)
	public void removeRightHandSide_noEquals(){
		String lhs = StringSeparator.removeRightHandSide("Test Test");	
	}
	
	@Test(expected = InvalidParameterException.class)
	public void removeRightHandSide_multipleEquals(){
		String lhs = StringSeparator.removeRightHandSide("Test == Test == Test");	
	}
	
	@Test
	public void removeRightHandSide_validInput(){
		String lhs = StringSeparator.removeRightHandSide("Test == Test");
		assertTrue(lhs.equals("Test"));
		
	}
	
	@Test(expected = InvalidParameterException.class)
	public void countNumberOfOccurencesOfStringInString_nullInputString(){
		StringSeparator.countNumberOfOccurencesOfStringInString(null, "test");		
	}
	
	@Test(expected = InvalidParameterException.class)
	public void countNumberOfOccurencesOfStringInString_nullInputSubString(){
		StringSeparator.countNumberOfOccurencesOfStringInString("test", null);		
	}
	
	@Test
	public void countNumberOfOccurencesOfStringInString_noOccurrences(){
		int testOutput = StringSeparator.countNumberOfOccurencesOfStringInString("test", "==");
		assertEquals(testOutput,0);
	}
	
	@Test
	public void countNumberOfOccurencesOfStringInString_singleOccurrence(){
		int testOutput = StringSeparator.countNumberOfOccurencesOfStringInString("test == test", "==");
		assertEquals(testOutput,1);
	}
	
	@Test
	public void countNumberOfOccurencesOfStringInString_multipleOccurrences(){
		int testOutput = StringSeparator.countNumberOfOccurencesOfStringInString("test == test == test", "==");
		assertEquals(testOutput,2);
	}
	
	@Test
	public void setLeftHandSide_validInputNoPluses(){
		separator = new StringSeparator("LHS == RHS");
		assertTrue(separator.getLeftHandSide().size() == 1);
		assertTrue(separator.getLeftHandSide().get(0).equals("LHS"));
	}
	
	@Test
	public void setLeftHandSide_validInput1Plus(){
		separator = new StringSeparator("LHS + LHS2 == RHS");
		assertTrue(separator.getLeftHandSide().size() == 2);
		assertTrue(separator.getLeftHandSide().get(0).equals("LHS"));
		assertTrue(separator.getLeftHandSide().get(1).equals("LHS2"));
	}
	
	@Test
	public void setLeftHandSide_validInput3Pluses(){
		separator = new StringSeparator("LHS + LHS1 + LHS2 + LHS3 == RHS");
		assertTrue(separator.getLeftHandSide().size() == 4);
		assertTrue(separator.getLeftHandSide().get(0).equals("LHS"));
		assertTrue(separator.getLeftHandSide().get(1).equals("LHS1"));
		assertTrue(separator.getLeftHandSide().get(2).equals("LHS2"));
		assertTrue(separator.getLeftHandSide().get(3).equals("LHS3"));
	}


}
