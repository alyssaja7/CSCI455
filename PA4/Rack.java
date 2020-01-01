
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
   A Rack of Scrabble tiles
 */

public class Rack {
	String word;
	String unique = "";
	int[] mult;
	Map<Character, Integer> map;

	public Rack(String word) {
		this.word = word;
	}

	/**
	 * Get all the subsets of one word, except the empty string. First, we get the
	 * mult and unique. Unique is a string which stores each unique character in one
	 * word. mult stores the number of each unique character in the word.
	 * 
	 */
	public ArrayList<String> getAllSubsets(String word) {
		// This map is used to store every unique character of the word and the number
		// of each character in the word.
		// Key: every unique character of the word
		// Value: the number of each unique character in the word
		map = new HashMap<Character, Integer>();
		for (int i = 0; i < word.length(); i++) {
			if (Character.isLetter(word.charAt(i))) {  // deal with the word including some special character like
														   // @,1,-,etc.
				Integer count = map.get(word.charAt(i));
				// determine if this character has already been in the map
				if (count == null) {
					map.put(word.charAt(i), 1);        // if this character isn't in the map, set the count as 1;
				} else {
					map.put(word.charAt(i), count + 1);   // if it is, just keeping add the number every time.
				}
			}
		}
		mult = new int[map.keySet().size()];
		int i = 0;
       
		// get mult and unique of one word in order to prepare for getting all of the subsets.
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			unique += entry.getKey();
			mult[i] = entry.getValue();
			i++;
		}

		ArrayList<String> allSubsets = allSubsets(unique, mult, 0); // get all of the subsets of one word
		// remove the empty set
		for (int m = 0; m < allSubsets.size(); m++) {
			if ("".equals(allSubsets.get(m))) {
				allSubsets.remove(m);
			}
		}
		return allSubsets;
	}

	/**
	 * Finds all subsets of the multiset starting at position k in unique and mult.
	 * unique and mult describe a multiset such that mult[i] is the multiplicity of
	 * the char unique.charAt(i). PRE: mult.length must be at least as big as
	 * unique.length() 0 <= k <= unique.length()
	 * 
	 * @param unique a string of unique letters
	 * @param mult   the multiplicity of each letter from unique.
	 * @param k      the smallest index of unique and mult to consider.
	 * @return all subsets of the indicated multiset. Unlike the multiset in the
	 *         parameters, each subset is represented as a String that can have
	 *         repeated characters in it.
	 * @author Claire Bono
	 */
	private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
		ArrayList<String> allCombos = new ArrayList<>();

		if (k == unique.length()) {    // multiset is empty
			allCombos.add("");
			return allCombos;
		}

		// get all subsets of the multiset without the first unique char
		ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

		// prepend all possible numbers of the first char (i.e., the one at position k)
		// to the front of each string in restCombos. Suppose that char is 'a'...

		String firstPart = "";       // in outer loop firstPart takes on the values: "", "a", "aa", ...
		for (int n = 0; n <= mult[k]; n++) {
			for (int i = 0; i < restCombos.size(); i++) {    // for each of the subsets
															  // we found in the recursive call
				// create and add a new string with n 'a's in front of that subset
				allCombos.add(firstPart + restCombos.get(i));
			}
			firstPart += unique.charAt(k); // append another instance of 'a' to the first part
		}
		return allCombos;
	}
}
