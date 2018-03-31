package com.bwardweb.cryptarithmetic;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.After;
import org.junit.Test;

public class StringSeparatorTest {
	
	private StringSeparator separator;
	
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
		assertEquals("RHS", separator.getRightHandSide());
	}
	
	@Test(expected = InvalidParameterException.class)
	public void removeRightHandSide_nullInput(){
		StringSeparator.removeRightHandSide(null);
	}
	
	@Test(expected = InvalidParameterException.class)
	public void removeRightHandSide_emptyInput(){
		StringSeparator.removeRightHandSide("");
	}
	
	@Test(expected = InvalidParameterException.class)
	public void removeRightHandSide_noEquals(){
		StringSeparator.removeRightHandSide("Test Test");
	}
	
	@Test(expected = InvalidParameterException.class)
	public void removeRightHandSide_multipleEquals(){
		StringSeparator.removeRightHandSide("Test == Test == Test");
	}
	
	@Test
	public void removeRightHandSide_validInput(){
		String lhs = StringSeparator.removeRightHandSide("Test == Test");
		assertEquals("Test", lhs);
		
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
		assertEquals(1, separator.getLeftHandSide().size());
		assertEquals("LHS", separator.getLeftHandSide().get(0));
	}
	
	@Test
	public void setLeftHandSide_validInput1Plus(){
		separator = new StringSeparator("LHS + LHS2 == RHS");
		assertEquals(2, separator.getLeftHandSide().size());
		assertEquals("LHS", separator.getLeftHandSide().get(0));
		assertEquals("LHS2", separator.getLeftHandSide().get(1));
	}
	
	@Test
	public void setLeftHandSide_validInput3Pluses(){
		separator = new StringSeparator("LHS + LHS1 + LHS2 + LHS3 == RHS");
		assertEquals(4, separator.getLeftHandSide().size());
		assertEquals("LHS", separator.getLeftHandSide().get(0));
		assertEquals("LHS1", separator.getLeftHandSide().get(1));
		assertEquals("LHS2", separator.getLeftHandSide().get(2));
		assertEquals("LHS3", separator.getLeftHandSide().get(3));
	}


}
