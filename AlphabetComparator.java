package edu.iastate.cs228.hw2;

import java.util.Comparator;


/**
 * A string comparator that uses an ordering of an {@link Alphabet} to
 * determine how to compare individual characters.
 * 
 * @author Ryan Scehovic
 */
public class AlphabetComparator  implements Comparator<String>{
  /**
   * The ordering used to compare characters.
   */

  private Alphabet alphabet;


  /**
   * Constructs and initializes the comparator to use the given ordering.
   * 
   * @param ordering
   *   the ordering to use to compare characters
   * @throws NullPointerException
   *   if {@code ordering} is {@code null}
   */
  public AlphabetComparator(Alphabet ordering) throws NullPointerException
  {
    // TODO
	  if(ordering == null) {
		  throw new NullPointerException();
	  }
	  alphabet = ordering;
  }


  /**
   * Compares the two given strings based on the ordering used by this
   * comparator.
   * 
   * Returns a negative value if the first string is considered less than the
   * second, a positive value if greater than the second, and zero if the two
   * strings are equal. Note that an exception must be thrown if the strings
   * contain invalid characters, even if the two strings are equal.
   * 
   * For each character of the given strings, the ordering is consulted to
   * determine which of the two characters should go first, with the one with a
   * lesser position in the ordering being determined to be lesser. If the
   * position of the characters are the same, the next character is examined.
   * After the end of one of the strings is reached, the shorter string is
   * considered to be lesser than the other.
   * 
   * @throws NullPointerException
   *   if either of {@code a} or {@code b} are {@code null}
   * @throws IllegalArgumentException
   *   if either of {@code a} or {@code b} contain a character not found in
   *   this comparator's ordering
   */
  @Override
  public int compare(String a, String b) throws NullPointerException, IllegalArgumentException{
    // TODO
//	if(a == null || b == null) { //if either null throw exception
//		throw new NullPointerException();
//	}
	
	//for loop goes through the 2 strings for the smaller of the 2 lengths
	int min = Math.min(a.length(), b.length());
	
	for(int i = 0; i < min; i++) {
		if(!(alphabet.isValid(a.charAt(i)) || alphabet.isValid(b.charAt(i)))) {
			throw new IllegalArgumentException();
		}
		if(alphabet.getPosition(a.charAt(i)) < alphabet.getPosition(b.charAt(i))) {
			return -1;
		}
		else if (alphabet.getPosition(a.charAt(i)) > alphabet.getPosition(b.charAt(i))) {
			return 1;
		}
	}
	if(a.length() > b.length()) { //only gets here if for the smaller of the two lengths, the two Strings are the same, like if a = dog and b = dogggg
		return 1;                 // example above - if a = dog & b = dogggg we want to return -1
	}
	else if(a.length() < b.length()) { 
		return -1;
	}
	return 0; //if none of previous if statements cause returns, then we know the 2 strings are equal
	
   }  
}
