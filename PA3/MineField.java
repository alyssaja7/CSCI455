import java.util.Random;


/** 
MineField
   class with locations of mines for a game.
   This class is mutable, because we sometimes need to change it once it's created.
   mutators: populateMineField, resetEmpty
   includes convenience method to tell the number of mines adjacent to a location.
   
   @param mineField	a 2D array used to receive data of mines
   @param numRows	the number of rows
   @param numCols	the number of columns
   @param numMines	the total number of mines in mineField
*/
public class MineField {

	// <put instance variables here>
		private boolean[][] mineField;
		private int numRows;
		private int numCols;
		private int numMines;
   
	/**
	 * Create a minefield with same dimensions as the given array, and populate it
	 * with the mines in the array such that if mineData[row][col] is true, then
	 * hasMine(row,col) will be true and vice versa. numMines() for this minefield
	 * will corresponds to the number of 'true' values in mineData.
	 * 
	 * @param mineData the data for the mines; must have at least one row and one col.
	 */
	public MineField(boolean[][] mineData) {
		
		int numMines = 0; 
		mineField = new boolean[mineData.length][mineData[0].length]; // initialize the mineField
		for (int i = 0; i < mineData.length; i++) {
			for (int j = 0; j < mineData[0].length; j++) {
				if (mineData[i][j]) {
					numMines++;
				} 
				mineField[i][j] = mineData[i][j];
			}
		}
		numRows = mineData.length;
		numCols = mineData[0].length;
		this.numMines = numMines;
	}

	/**
	 * Create an empty minefield (i.e. no mines anywhere), that may later have
	 * numMines mines (once populateMineField is called on this object). Until
	 * populateMineField is called on such a MineField, numMines() will not
	 * correspond to the number of mines currently in the MineField.
	 * 
	 * @param numRows  number of rows this minefield will have, must be positive
	 * @param numCols  number of columns this minefield will have, must be positive
	 * @param numMines number of mines this minefield will have, once we populate
	 *                 it. 
     * PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations).
	 */
	public MineField(int numRows, int numCols, int numMines) {
       
		mineField = new boolean[numRows][numCols]; // initialize the mineField with the default value
		this.numMines = numMines; 							
		this.numRows = numRows;
		this.numCols = numCols;
	}

	/**
	 * Removes any current mines on the minefield, and puts numMines() mines in
	 * random locations on the minefield, ensuring that no mine is placed at (row,
	 * col).
	 * 
	 * @param row the row of the location to avoid placing a mine
	 * @param col the column of the location to avoid placing a mine 
     *
	 * PRE: inRange(row, col)
	 */
	public void populateMineField(int row, int col) {
		resetEmpty();
		int currentNumOfMines = 0;
		int newRows;
		int newCols;
		Random generator = new Random();
		while (currentNumOfMines < numMines) {
			newRows = generator.nextInt(numRows); 												
			newCols = generator.nextInt(numCols);
			//put the mine
			if ((newRows != row && newCols != col) && (mineField[newRows][newCols] != true)) {
				mineField[newRows][newCols] = true; // put the mine
				currentNumOfMines++;
			}
		}
	}

	/**
	 * Reset the minefield to all empty squares. This does not affect numMines(),
	 * numRows() or numCols() Thus, after this call, the actual number of mines in
	 * the minefield does not match numMines(). 
     * Note: This is the state the minefield is in at the beginning of a game.
	 */
	public void resetEmpty() {
		mineField = new boolean[numRows][numCols];
	}

	/**
	 * Returns the number of mines adjacent to the specified mine location (not
	 * counting a possible mine at (row, col) itself). Diagonals are also considered
	 * adjacent, so the return value will be in the range [0,8]
	 * 
	 * @param row row of the location to check
	 * @param col column of the location to check
	 * @return the number of mines adjacent to the square at (row, col) 
	 * PRE: inRange(row, col)
	 */
	public int numAdjacentMines(int row, int col) {
       
		assert inRange(row, col);    // limit the range of row and column to avoid ArrayIndexOutOfBounds
		int[][] eightPos = { { row - 1, col - 1 }, { row - 1, col }, { row - 1, col + 1 }, { row, col - 1 },
				{ row, col + 1 }, { row + 1, col - 1 }, { row + 1, col }, { row + 1, col + 1 } };   
                                     //a 2D array represents the location of eight squares around the center (row,col)
		int numNeighbour = 0; 
		for (int i = 0; i < eightPos.length; i++) {
			int x = eightPos[i][0];    // get the x value of eight position
			int y = eightPos[i][1];    // get the y value of eight position
			//Count the number of adjacent mine by traversing the eight position around the center one
			if (inRange(x, y) && mineField[x][y]) {
				numNeighbour++;
			}
		}
		return numNeighbour;
	}

	/**
	 * Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
	 * start from 0.
	 * @param row  row of the location to consider
	 * @param col  column of the location to consider
     *
	 * @return whether (row, col) is a valid field location
	 */
	public boolean inRange(int row, int col) {
       
		if (row >= 0 && row <= numRows - 1 && col >= 0 && col <= numCols - 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the number of rows in the field.
	 * 
	 * @return number of rows in the field
	 */
	public int numRows() {
		return numRows;
	}

	/**
	 * Returns the number of columns in the field.
	 * 
	 * @return number of columns in the field
	 */
	public int numCols() {
		return numCols;
	}


	/**
     * Returns whether there is a mine in this square
     * @param row  row of the location to check
     * @param col  column of the location to check
     * @return whether there is a mine in this square
     *
   	 * PRE: inRange(row, col)   
	 */ 
	public boolean hasMine(int row, int col) {
		if (mineField[row][col] == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
	   some of the time this value does not match the actual number of mines currently on the field.  See doc for that
	   constructor, resetEmpty, and populateMineField for more details.
	 * @return
	 */
	public int numMines() {
		return numMines;
	}

	// <put private methods here>
}
