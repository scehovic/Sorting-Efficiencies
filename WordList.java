package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.*;
import java.util.*
;/**
 * A simple list of Strings.
 * 
 * @author Ryan Scehovic
 */
public class WordList implements Cloneable {
  /**
   * The array holding all of the elements of the list.
   */
  private String[] words;


  /**
   * Constructs and initializes the list to have exactly the same contents as
   * the given array.
   * 
   * @param contents
   *   the array with the contents of the new list
   * @throws NullPointerException
   *   if {@code contents} is {@code null}
   */
  public WordList(String[] contents) throws NullPointerException
  {
	//TODO
	  
	if(contents == null) { //if null, then throw NPE
		throw new NullPointerException();
	}
	words = new String[contents.length]; 
	
	for(int i = 0; i < words.length; i++) { //move values from contents array to words array
		words[i] = contents[i];
	}
	
	//words = contents.clone();
  }

  /**
   * Constructs and initializes the list by reading from the indicated file.
   * The file is read assuming that each line contains a word. The ordering in
   * the file is the order that will be used by the list.
   * 
   * @param filename
   *   the name of the file to read
   * @throws NullPointerException
   *   if {@code filename} is {@code null}
   * @throws FileNotFoundException
   *   if the file cannot be found
   */
  public WordList(String filename) throws NullPointerException, FileNotFoundException
  {
    // TODO
	try {
		if(filename == null) {  //throw NPE if file isn't named when sent in as parameter to constructor
			throw new NullPointerException();
		}
		
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		List<String> word = new ArrayList<>();
		while(sc.hasNextLine()) { 
			word.add(sc.nextLine());
		}
		sc.close();
		words = new String[word.size()];
		
		for(int i = 0; i < word.size(); i++) {
			words[i] = word.get(i);
		}
	}
	catch (Exception e){
		throw new FileNotFoundException();
	}
  }


  /**
   * Returns the number of elements in the list.
   * 
   * @return
   *   the number of elements in the list
   */
  public int length()
  {
    // TODO
    return words.length;
  }

  /**
   * Returns the element of the list at the indicated index.
   * 
   * @param idx
   *   the index of the element to retrieve
   * @return
   *   the element at the indicated index
   * @throws IndexOutOfBoundsException
   *   if {@code idx} is negative or greater than or equal to the length of
   *   the list
   */
  public String get(int idx) throws IndexOutOfBoundsException
  {
    // TODO
	if(idx >= words.length || idx < 0) { //if any conditions met, index is out of bounds
		throw new IndexOutOfBoundsException();
	}
    return words[idx];
  }

  /**
   * Sets the element of the list at the indicated index to the given value.
   * 
   * @param idx
   *   the index of the element to set
   * @param newValue
   *   the new value of the element
   * @throws IndexOutOfBoundsException
   *   if {@code idx} is negative or greater than or equal to the length of the
   *   list
   */
  public void set(int idx, String newValue) throws IndexOutOfBoundsException
  {
    // TODO
	if(idx > words.length-1 || idx < 0) { //if any conditions met, index is out of bounds
		throw new IndexOutOfBoundsException();
	}
	words[idx] = newValue;
  }

  /**
   * Swaps the indicated elements in the list.
   * 
   * @param idxA
   *   the index of one of the elements to swap
   * @param idxB
   *   the index of the other element to swap
   * @throws IndexOutOfBoundsException
   *   if either of {@code idxA} or {@code idxB} is negative or greater than or
   *   equal to the length of the list
   */
  public void swap(int idxA, int idxB) throws IndexOutOfBoundsException
  {
    // TODO
	if(idxA > words.length-1 || idxA < 0 || idxB > words.length-1 || idxB < 0) { //if any conditions met, index is out of bounds
		throw new IndexOutOfBoundsException();
	}
	// if(idxA == idxB) return;
	
	//swapping values, need String to store 1 value to successfully perform swap
	String wordA = words[idxA];
	words[idxA] = words[idxB];
	words[idxB] = wordA;
  }

  /**
   * Returns the array used by the list to store its elements.
   * 
   * @return
   *   the array used by the list to store its elements
   */
  public String[] getArray()
  {
    // TODO
    return words;
  }

  /**
   * Performs a deep copy of the list.
   */
  @Override
  public WordList clone()
  {
    /*
     * note: since Strings are immutable, you don't need to clone them
     */

    // TODO
	WordList wl = new WordList(this.words);
    return wl;
  }
}
