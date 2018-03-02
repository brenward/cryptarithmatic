package com.bwardweb.cryptarithmetic;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class StringSeparator {
	private static final String equals = "=="; 
	
	private List<String> leftHandSide;
	private String rightHandSide;
	
	public StringSeparator(String input){
		leftHandSide = new ArrayList<String>();
		setLeftHandSide(input);
		setRightHandSide(input);
	}
	
	private void setLeftHandSide(String input){
		String uncutLeftHandSide = removeRightHandSide(input);
		
		do{
			if(uncutLeftHandSide.contains("+")){
				leftHandSide.add(uncutLeftHandSide.substring(0,uncutLeftHandSide.indexOf("+") - 1));
				uncutLeftHandSide = uncutLeftHandSide.substring(uncutLeftHandSide.indexOf("+") + 2);
			}else{
				leftHandSide.add(uncutLeftHandSide);
				uncutLeftHandSide = null;
			}			
		}while(uncutLeftHandSide != null);			
	}
	
	private void setRightHandSide(String input){
		if(input == null || input.equals("")){
			throw new InvalidParameterException("Input cannot be null or empty");
		}else if(countNumberOfOccurencesOfStringInString(input,equals) != 1){
			throw new InvalidParameterException("Incorrect number of equals detected");
		}else{
			int beginIndex = input.lastIndexOf(equals);
			rightHandSide = input.substring(beginIndex + 3);
		}
	}

	public List<String> getLeftHandSide() {
		return leftHandSide;
	}

	public String getRightHandSide() {
		return rightHandSide;
	}
	
	public static String removeRightHandSide(String input){
		if(input == null || input.equals("")){
			throw new InvalidParameterException("Input cannot be null");
		}else if(countNumberOfOccurencesOfStringInString(input,equals) != 1){
			throw new InvalidParameterException("Input cannot be null");
		}else{
			return input.substring(0, input.indexOf(equals) - 1);
		}
	}
	
	public static int countNumberOfOccurencesOfStringInString(String inputString, String subString){
		if(inputString == null || subString == null){
			throw new InvalidParameterException("Inputs cannot be null");
		}else{
			int count = 0;
			for(int i=0;i<inputString.length();i++){
				if(inputString.indexOf(subString, i) != -1){
					count++;
					i = inputString.indexOf(subString, i);
				}
			}
			return count;
		}
	}
	
	
}
