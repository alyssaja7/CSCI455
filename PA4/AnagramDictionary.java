

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A dictionary of all anagram sets. Note: the processing is case-sensitive; so
 * if the dictionary has all lower case words, you will likely want any string
 * you test to have all lower case letters too, and likewise if the dictionary
 * words are all upper case.
 */

public class AnagramDictionary {
	Map<String, ArrayList<String>> wordMap;

	/**
	 * Create an anagram dictionary from the list of words given in the file
	 * indicated by fileName. PRE: The strings in the file are unique.
	 * 
	 * @param fileName the name of the file to read from
	 * @throws FileNotFoundException if the file is not found
	 */

	public AnagramDictionary(String fileName) throws FileNotFoundException {
		wordMap = new HashMap<String, ArrayList<String>>();    // keySet is the sorted word. 
															   // ValueSet is anagrams of this word.
		File file = new File(fileName);
		Scanner in = new Scanner(file);

		while (in.hasNext()) {
			String word = in.next();
			in.nextLine();
			char[] charArr = word.toCharArray();
			Arrays.sort(charArr);                              // sort the word in alphabetical order.
			String afterSort = String.valueOf(charArr);
			if (!wordMap.containsKey(afterSort)) {
				ArrayList<String> arrayList = new ArrayList<String>();
				arrayList.add(word);                        // If the sorted word is not in the wordMap, 
                                                            //build an arrayList and add the word.
				wordMap.put(afterSort, arrayList);       // Update the map
			} else {
				wordMap.get(afterSort).add(word); // If the word has already been in the wordMap, add the word to the
													    // existing arrayList corresponding to sorted word.
			}
		}
		in.close();

	}

	/**
	 * Get all anagrams of the given string. This method is case-sensitive. E.g.
	 * "CARE" and "race" would not be recognized as anagrams.
	 * 
	 * @param s string to process
	 * @return a list of the anagrams of s
	 */
	public ArrayList<String> getAnagramsOf(String s) {
		char[] charArr = s.toCharArray();
		Arrays.sort(charArr);
		String afterSort = String.valueOf(charArr);

		if (wordMap.get(afterSort) == null) {
			return null;       // In the given dictionary wordMap, if the anagrams of sorted word is null,
							   // return null.
		} else {
			return new ArrayList<String>(wordMap.get(afterSort)); // Otherwise, return the corresponding anagrams list.
		}
	}

}