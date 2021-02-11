package edu.iastate.cs228.hw2;

import java.util.Comparator;
import java.io.*;

/**
 * An class that compares various methods of sorting.
 * 
 * @author Ryan Scehovic
 */
public class SorterFramework
{
  /**
   * Loads data necessary to run the sorter statistics output, and runs it.
   * The only logic within this method should be that necessary to use the
   * given file names to create the {@link AlphabetComparator},
   * {@link WordList}, and sorters to use, and then using them to run the
   * sorter statistics output.
   * 
   * @param args
   *   an array expected to contain two arguments:
   *    - the name of a file containing the ordering to use to compare
   *      characters
   *    - the name of a file containing words containing only characters in the
   *      other file
   */
 public static void main(String[] args) throws NullPointerException, FileNotFoundException, IllegalArgumentException, CloneNotSupportedException
  {
    // TODO check arguments
	File one = new File(args[0]);
	File two = new File(args[1]);
	if(one == null || two == null) {
		throw new FileNotFoundException();
	}
    Alphabet alphabet = new Alphabet(args[0]);
    AlphabetComparator comparator = new AlphabetComparator(alphabet);
    WordList words;
    Sorter[] sorters = {new InsertionSorter(), new MergeSorter(), new QuickSorter()};
    words = new WordList(args[1]);

    // TODO create appropriate values
    SorterFramework toRun = new SorterFramework(sorters, comparator, words, 1000000);
    toRun.run();
  }


  /**
   * The comparator to use for sorting.
   */
  private Comparator<String> comparator;

  /**
   * The words to sort.
   */
  private WordList words;

  /**
   * The array of sorters to use for sorting.
   */
  private Sorter[] sorters;

  /**
   * The total amount of words expected to be sorted by each sorter.
   */
  private int totalToSort;


  /**
   * Constructs and initializes the SorterFramework.
   * 
   * @param sorters
   *   the array of sorters to use for sorting
   * @param comparator
   *   the comparator to use for sorting
   * @param words
   *   the words to sort
   * @param totalToSort
   *   the total amount of words expected to be sorted by each sorter
   * @throws NullPointerException
   *   if any of {@code sorters}, {@code comparator}, {@code words}, or
   *   elements of {@code sorters} are {@code null}
   * @throws IllegalArgumentException
   *   if {@code totalToSort} is negative
   */
  public
  SorterFramework(Sorter[] sorters, Comparator<String> comparator, WordList words, int totalToSort) throws NullPointerException, IllegalArgumentException
  {
    // TODO
	this.comparator = comparator;
	this.totalToSort = totalToSort;
	this.sorters = sorters;
	this.words = words;
  }


  /**
   * Runs all sorters using
   * {@link Sorter#sortWithStatistics(WordList, Comparator, int)
   * sortWithStatistics()}, and then outputs the following information for each
   * sorter:
   *  - the name of the sorter
   *  - the length of the word list sorted each time
   *  - the total number of words sorted
   *  - the total time used to sort words
   *  - the average time to sort the word list
   *  - the number of elements sorted per second
   *  - the total number of comparisons performed
   */
  public void run() {
    // TODO
	for(int i = 0; i < 3; i++) {
		sorters[i].sortWithStatistics(words, comparator, totalToSort);
		System.out.println(sorters[i].getName() + ":");
		System.out.println("Length of word list: " + words.length());
		System.out.println("Total words sorted: " + sorters[i].getTotalWordsSorted());
		System.out.println("Time to sort: " + sorters[i].getTotalSortingTime() + " ms");
		
		if(sorters[i].getTotalSortingTime()/1000 <= 0) {
			System.out.println("Average sorting speed: " + sorters[i].getTotalWordsSorted() + " words per second");
		}
		else {
			System.out.println("Average sorting speed: " + (sorters[i].getTotalWordsSorted() / sorters[i].getTotalSortingTime()/1000)+ " words per second");
		}
		
		System.out.println("Total Comparisons: " + sorters[i].getTotalComparisons());
		System.out.println();
		System.out.println();
	}
  }
}
