


import java.util.Comparator;

/**
 * This program is used to sort the words. First, sort the words according to
 * their corresponding scores. If there are two words with same score, then sort
 * them in alphabetical order.
 * 
 */

public class WordComparator implements Comparator<String> {
	ScoreTable scoreTable = new ScoreTable();

	@Override
	public int compare(String m, String n) {
		int mScore = scoreTable.getScore(m);
		int nScore = scoreTable.getScore(n);
		if (mScore != nScore) {
			return nScore - mScore; // if the score of word m does not equal to the score of word n, compare them.
		} else {
			return m.compareTo(n); // sort them in alphabetical order
		}

	}
}