package edu.iastate.cs228.hw2;

import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs merge sort
 * to sort the list.
 * 
 * @author Ryan Scehovic
 */
public class MergeSorter extends Sorter
{
	/**
	 * sort checks if null, if not null continue to sort recursive sort method
	 */
  @Override
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException{
    // TODO
	if(comp == null || toSort == null) {
		throw new NullPointerException();
	}
	mergeSortRec(toSort,comp,0,toSort.length()-1);
  }
/**
 * merge sort recursion which makes 2 recusive calls each time and then the helper method combines everything
 * @param list
 * @param comp
 * @param start
 * @param end
 */
  private void mergeSortRec(WordList list, Comparator<String> comp, int start, int end)
  {
    // TODO
	  int middle = start + (end - start)/2; 
	  
	  if(start < end) {
		  mergeSortRec(list, comp, start, middle);
		  mergeSortRec(list, comp, middle+1, end);
		  mergeHelp(list, comp, start, middle, end);
	  }
	  
  }
  /**
   *  merge helper method that swaps values to appropriate places
   * @param list
   * @param comp
   * @param start
   * @param mid
   * @param end
   */
  private void mergeHelp(WordList list, Comparator<String> comp, int start, int mid, int end) {
	  
	  
	  int pos = 0;
	  int a = start;
	  int len = end - start + 1;
	  int b = mid + 1;
	  String[] sorted = new String[len];
	  
	  while(a <= mid && b <= end) {
		  if(comp.compare(list.get(a), list.get(b)) == -1) { 
			  sorted[pos] = list.get(b);
			  b++;
		  }
		  else {
			  sorted[pos] = list.get(a);
			  a++;
		  }
		  pos++;
	  }
	  
	  while(a <= mid) {
		  sorted[pos] = list.get(a);
		  a++;
		  pos++;
	  }
	  while(b <= end) {
		  sorted[pos] = list.get(b);
		  b++;
		  pos++;
	  }
	  for(int i = 0; i < len; i++) {
		  list.set(i + start, sorted[i]);
	  }
  }
}
