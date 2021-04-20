package StringMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;




/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		String longestString = "";
		
		if(s1.length() > s2.length()){
			longestString = s1;
		}else{
			longestString = s2;
		}
		
		return longestString;
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		char letter;
		String sentence = "";
		if(s.contains("underscores")){
			for(int i = 0; i<s.length(); i++){
				letter = s.charAt(i);
				if(letter == ' '){
					sentence += '_';
				}else{
					sentence += letter;
				}
			}
		}else{
			sentence = s;
		}
		return sentence;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		String firstName = "";
		String s1New = s1.trim();
		String s2New = s2.trim();
		String s3New = s3.trim();
		
		char s1LastName = s1New.charAt(s1New.length()-1);
		char s2LastName = s2New.charAt(s2New.length()-1);
		char s3LastName = s3New.charAt(s3New.length()-1);

		if(s1LastName < s2LastName && s1LastName < s3LastName){
			firstName = s1New;
		}else if(s2LastName < s1LastName && s2LastName > s3LastName){
			firstName = s2New;
		}
		firstName = s3New;
		
		return firstName;
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int sum = 0;
		int num;
		String Snew ="";
		
		for(int i =0; i< s.length();i++){
			if(Character.isDigit(s.charAt(i)) == true){
				Snew = "" + s.charAt(i);
				num = Integer.parseInt(Snew);
				sum += num;
			}
		}
		
		return sum;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int subNum=0;
		
			for(int i =0; i<s.length()-substring.length()+1; i++){
				if(s.substring(i, i+substring.length()).equals(substring)){
					subNum++;
				}
			}
				
		return subNum;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		return Utilities.encrypt(s.getBytes(), (byte) key);
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) key);
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		//ArrayList<String> wordsEnds = new ArrayList<>();
		int wordsEnds = 0;
		String[] words = s.split(" ");
		
		for(int i =0; i<words.length; i++){
			for(int j =0; j<words[i].length()-substring.length()+1; j++){
				if(words[i].substring(j+substring.length(), words[i].length()).equals(substring)){
					wordsEnds++;
				}
			}
		}
		
		
		return wordsEnds;
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		
		
		int firstOIndex = 0;
		int lastOIndex = 0;
		
		
		//Finding the first occurrence
			for(int i =0; i<s.length()-substring.length()+1; i++){
				if(s.substring(i, i+substring.length()).equals(substring)){
					firstOIndex = i+substring.length();
					break;
				}
			}
			
		//Finding the last occurrence
			for(int i =s.length() - substring.length(); i>=0; i--){
				if(s.substring(i, i+substring.length()).equals(substring)){
					lastOIndex = i;
					break;
				}
			}
		
			int numOfChar = lastOIndex - firstOIndex;
		
		return numOfChar;
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		String raw = "";
		String reversed = "";
		
		// removing space and punctuation of s
		for(int i =0; i<s.length();i++){
			char c = s.charAt(i);
			
			if(Character.isAlphabetic(c)){
				raw += Character.toLowerCase(c);
			}
		}
		
		
		//reversing and comparing raw & reverse
		for(int j=raw.length()-1; j>=0;j--){
			reversed += raw.charAt(j);
		}
		
		if(reversed.equals(raw)){
			System.out.println("true");
			return true;
		}
		
		System.out.println(raw);
		System.out.println(reversed);
		System.out.println("false");
		
		return false;
	}
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
