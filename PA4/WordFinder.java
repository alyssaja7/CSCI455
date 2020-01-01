
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The user input one string. This program reads the legal word. Then get all
 * anagrams of this word and word's subsets. Print them out according to the
 * decreasing score. If the file does not exist, an error information will
 * appear.
 */
public class WordFinder {
	public static void main(String[] args) {
		String dicName = "sowpods.txt"; 	// default file name
		AnagramDictionary dictionary = null;
		Rack rack;

		// argument command line
		try {
			if (args.length > 0) {
				dicName = args[0];
			}
			dictionary = new AnagramDictionary(dicName);

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found " + dicName);
			System.exit(0);
		}

		Scanner in = new Scanner(System.in);
		System.out.println("Type . to quit.");
		String input = "";

		while (true) {
			System.out.print("Rack? ");
			input = in.next();
			
			if (input.equals(".")) {
				return;    // if the input is ".", then quit.
			}
			rack = new Rack(input);
			ArrayList<String> subsets = rack.getAllSubsets(input); // get all subsets of the specific input
			ArrayList<String> allSubsets = new ArrayList<String>();

			// get all anagrams of one input(including its subsets) in the dictionary.
			for (String string : subsets) {
				if (dictionary.getAnagramsOf(string) != null) {
					allSubsets.addAll(dictionary.getAnagramsOf(string));
				}
			}
			int numOfTotal = allSubsets.size(); // the number of anagrams which we finally get.
			ScoreTable scoreTable = new ScoreTable();

			System.out.println("We can make " + numOfTotal + " words from \"" + input + "\"");
			if (allSubsets.size() > 0) {
				System.out.println("All of the words with their scores (sorted by score):");
			}
			// use the comparator to sort the anagrams.
			// Print out the anagrams according to the decreasing score.
			Collections.sort(allSubsets, new WordComparator());
			for (String word : allSubsets) {
				System.out.println(scoreTable.getScore(word) + ": " + word);
			}
		}
	}
}
