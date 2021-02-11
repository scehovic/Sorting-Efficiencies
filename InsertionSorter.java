package edu.iastate.cs228.hw2;

import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs insertion sort
 * to sort the list.
 * 
 * @author Ryan Scehovic
 */
public class InsertionSorter extends Sorter
{
	
	/**
	 * sort method uses insertion sort to move through array and sort into the correct order
	 * @param WordList toSort
	 * @param Comparator<String> comp
	 */
  @Override
public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException
  {
    // TODO
	for(int i = 0; i < toSort.length()-1; i++) {
		int j = i;
		while(j > -1 && (comp.compare(toSort.get(j), toSort.get(j+1)) == -1)) { //go while k > -1 and compare returns negative number
			toSort.swap(j, j+1); //perform three variable swap
			j--; //decrease 1 each time 
		}
	}
	
  }
}
