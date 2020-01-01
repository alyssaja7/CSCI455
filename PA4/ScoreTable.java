
import java.util.Map;
import java.util.TreeMap;

public class ScoreTable {
	Map<Character, Integer> map;
	private static final int[] SCORE_TABLE = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 1, 0, 1, 1, 1, 1, 4, 4, 8, 4, 1, 0 };
	private static final char LOWER_A = 'a';
	private static final char UPPER_A = 'A';
	private static final char LOWER_Z = 'z';
	private static final char UPPER_Z = 'Z';

	/**
	 * Build score table for all the upperletters from A to Z and all the
	 * lowerletters from a to z
	 */
	public ScoreTable() {
		// For storing the letter and its value, build this map
		// key: letters including upperletter from A to Z and lowerletter from a to z
		// value: the corresponding value each character has
		map = new TreeMap<Character, Integer>();
		for (char ch = LOWER_A; ch < LOWER_Z + 1; ch++) {
			map.put(ch, SCORE_TABLE[ch - LOWER_A]);
		}
		for (char ch = UPPER_A; ch < UPPER_Z + 1; ch++) {
			map.put(ch, SCORE_TABLE[ch - UPPER_A]);
		}
	}

	/**
	 * get score of each word
     *
     * @param word
     * @return the score of the word
	 */
	public int getScore(String word) {
		Integer value = 0;
		for (int i = 0; i < word.length(); i++) {
			value += map.get(word.charAt(i));
		}
		return value;
	}
}
