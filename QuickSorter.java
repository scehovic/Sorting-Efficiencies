package edu.iastate.cs228.hw2;

import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs quick sort
 * to sort the list.
 * 
 * @author Ryan Scehovic
 */
public class QuickSorter extends Sorter
{
	/**
	 * the begining of quick sort - defines start and end and then passes onto quick sort with recusion method
	 * @param WordList toSort
	 * @param Comparator<String> comp
	 * @throws NullPointer exception
	 */
  @Override
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException{
    // TODO
	int start = 0;
	int end = toSort.length()-1;
	quickSortRec(toSort,comp, start, end);
  }
  /**
	 * recursive calls made until base case is reached
	 * @param WordList list
	 * @param Comparator<String> comp
	 */
  private void quickSortRec(WordList list, Comparator<String> comp, int start, int end){
    // TODO
	if(start >= end) {
		return;
	}
	int pivot = partition(list,comp,start,end);
	quickSortRec(list,comp,start,pivot-1);
	quickSortRec(list,comp,pivot+1,end);
  }

  /**
   *  reorder the array so that all elements with values less than the pivot come before the pivot, 
   *  while all elements with values greater than the pivot come after it
   * @param list
   * @param comp
   * @param start
   * @param end
   * @return i + 1
   */
  private int partition(WordList list, Comparator<String> comp, int start, int end){
    // TODO
	String piv = list.get(end);
	int i = start-1;
	for(int j = start; j < end; j++) {
		if(comp.compare(piv, list.get(j)) < 0) {
			i++;
			list.swap(i,j);
		}
	}
	list.swap(i+1, end);
	return i+1;  
  }
}
