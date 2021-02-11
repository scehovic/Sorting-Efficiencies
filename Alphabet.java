package edu.iastate.cs228.hw2;


import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;

/**
 * A class representing an ordering of characters that can be queried to know
 * the position of a given character.
 * 
 * @author Ryan Scehovic
 */
public class Alphabet {
	/**
	 * A lookup table containing characters and their positions. Sorted by the
	 * character of each entry.
	 */
	private CharAndPos[] lookup;

	/**
	 * Constructs and initializes the ordering to have exactly the ordering of the
	 * elements in the given array.
	 * 
	 * @param ordering the array containing the characters, in the ordering desired
	 * @throws NullPointerException if {@code ordering} is {@code null}
	 */
	public Alphabet(char[] ordering) throws NullPointerException {
		lookup = new CharAndPos[ordering.length];
		for (int i = 0; i < ordering.length; i++) {
			lookup[i] = new CharAndPos(ordering[i], i);
		}
		asciiSort(lookup);
	}

	/**
	 * Constructs and initializes the ordering by reading from the indicated file.
	 * The file is expected to have a single character on each line, and the
	 * ordering in the file is the order that will be used.
	 * 
	 * @param filename the name of the file to read
	 * @throws NullPointerException  if {@code filename} is {@code null}
	 * @throws FileNotFoundException if the file cannot be found
	 */
	public Alphabet(String filename) throws NullPointerException, FileNotFoundException {
		if (filename == null) {
			throw new NullPointerException("filename is null.");
		}
		File file = new File(filename);
		int count = 0;
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			count++;
			sc.next();
		}
		sc.close();
		lookup = new CharAndPos[count];
		Scanner sc2 = new Scanner(file);
		int j = 0;
		while (sc2.hasNext()) {
			lookup[j] = new CharAndPos(sc2.next().charAt(0), j);
			j++;
		}
		sc2.close();
		asciiSort(lookup);
	}

	/**
	 * Returns true if and only if the given character is present in the ordering.
	 * 
	 * @param c the character to test
	 * @return true if and only if the given character is present in the ordering
	 */
	public boolean isValid(char c) {
		// goes through to check binarySeach to see if its a valid character
		if (binarySearch(c) != -1) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the position of the given character in the ordering. Returns a
	 * negative value if the given character is not present in the ordering.
	 * 
	 * @param c the character of which the position will be determined
	 * @return the position of the given character, or a negative value if the given
	 *         character is not present in the ordering
	 */
	public int getPosition(char c) {
		if (isValid(c)) {
			// returns the position of the character inside lookup
			int index = binarySearch(c);
			return lookup[index].position;
		}
		return -1;

	}

	/**
	 * Returns the index of the entry containing the given character within the
	 * lookup table {@link #lookup}. Returns a negative value if the given character
	 * does not have an entry in the table.
	 * 
	 * @param toFind the character for which to search
	 * @return the index of the entry containing the given character, or a negative
	 *         value if the given character does not have an entry in the table
	 */
	private int binarySearch(char toFind) {
		/*
		 * note: for testing, you can perform a simple search through the array instead
		 * of a binary search, allowing you to test other components with a working (but
		 * slower) implementation
		 */
		int middle = 0;
		int low = 0;
		int high = lookup.length-1;
		while (high >= low) {
			middle = (high + low) / 2;
			if (lookup[middle].character < toFind) {
				low = middle + 1;
			} else if (lookup[middle].character > toFind) {
				high = middle - 1;
			} else {
				return middle;
			}
		}
		return -1;
	}

	/**
	 * Using ASCII sort
	 * 
	 * @param lookup
	 */
	private void asciiSort(CharAndPos[] lookup) {
		int small;
		CharAndPos temp;
		for (int i = 0; i < lookup.length - 1; i++) {
			small = i;
			for (int j = i + 1; j < lookup.length; ++j) {
				if (lookup[j].character < lookup[small].character) {
					small = j;
				}
			}
			temp = lookup[i]; //three variable swap
			lookup[i] = lookup[small];
			lookup[small] = temp;
		}
	}

	/**
	 * A PODT class containing a character and a position. Used as the entry type
	 * within {@link Alphabet#lookup lookup}.
	 */
	/* already completed */
	private static class CharAndPos {
		/**
		 * The character of the entry.
		 */
		public char character;

		/**
		 * The position of the entry in the ordering.
		 */
		public int position;

		/**
		 * Constructs and initializes the entry with the given values.
		 * 
		 * @param character the character of the entry
		 * @param position  the position of the entry
		 */
		public CharAndPos(char character, int position) {
			this.character = character;
			this.position = position;
		}

		@Override
		public boolean equals(Object obj) {
			if (null == obj || this.getClass() != obj.getClass()) {
				return false;
			}

			CharAndPos o = (CharAndPos) obj;

			return this.character == o.character && this.position == o.position;
		}

		@Override
		public int hashCode() {
			return character ^ position;
		}

		@Override
		public String toString() {
			return "{" + character + ", " + position + "}";
		}
	}
}