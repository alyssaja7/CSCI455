

/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield), Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to actions the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
	// ----------------------------------------------------------   
	// The following public constants (plus numbers mentioned in comments below) are the possible states of one
	// location (a "square") in the visible field (all are values that can be returned by public method 
	// getStatus(row, col)).
	   
	// Covered states (all negative values):
	public static final int COVERED = -1;   // initial value of all squares
	public static final int MINE_GUESS = -2;
	public static final int QUESTION = -3;

	// Uncovered states (all non-negative values):
	   
	// values in the range [0,8] corresponds to number of mines adjacent to this square
	   
	public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
	public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
	public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
	// ----------------------------------------------------------   
	  
	// <put instance variables here>
	private MineField mineField;		//the mineField that this VisibleField "covers"
	private int[][] visibleField;	    //an 2D array representing a mineField

	/**
       Create a visible field that has the given underlying mineField.
       The initial state will have all the mines covered up, no mines guessed, and the game
       not over.
       
       @param mineField  the minefield to use for for this VisibleField
	*/
	public VisibleField(MineField mineField) {
		
		this.mineField = mineField;
		visibleField = new int[mineField.numRows()][mineField.numCols()];
		for (int i = 0; i < mineField.numRows(); i++) {
			for (int j = 0; j < mineField.numCols(); j++) {
				visibleField[i][j] = COVERED; 	      //initial state
			}
		}
	}

	/**
       Reset the object to its initial state (see constructor comments), using the same underlying
       MineField. 
	*/ 
	public void resetGameDisplay() {
		for (int i = 0; i < mineField.numRows(); i++) {
			for (int j = 0; j < mineField.numCols(); j++) {
				visibleField[i][j] = COVERED;
			}
		}
	}

	/**
       Returns a reference to the mineField that this VisibleField "covers"
       @return the minefield
	*/
	public MineField getMineField() {
		return mineField;
	}

	/**
       Returns the visible status of the square indicated.
       @param row  row of the square
       @param col  col of the square
       @return the status of the square at location (row, col).  See the public constants at the beginning of the class
       for the possible values that may be returned, and their meanings.
       PRE: getMineField().inRange(row, col)
	*/
	public int getStatus(int row, int col) {
		return visibleField[row][col];
	}

	/**
       Returns the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
       or not.  Just gives the user an indication of how many more mines the user might want to guess.  This value can
       be negative, if they have guessed more than the number of mines in the minefield.     
       @return the number of mines left to guess.
    */
	public int numMinesLeft() {
		int currentMineGuess = 0; 	// the number of mines which have been set MINE_GUESS
		for (int i = 0; i < mineField.numRows(); i++) {
			for (int j = 0; j < mineField.numCols(); j++) {
				if (visibleField[i][j] == MINE_GUESS) {
					currentMineGuess++;
				}
			}
		}
		return mineField.numMines() - currentMineGuess;
	}

	/**
       Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
       changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
       changes it to COVERED again; call on an uncovered square has no effect.  
       
       @param row  row of the square
       @param col  col of the square
       PRE: getMineField().inRange(row, col)
    */
	public void cycleGuess(int row, int col) {
		//the logic of right click: all negative values
		if (getStatus(row, col) < 0) { 
			if (getStatus(row, col) == COVERED) {
				visibleField[row][col] = MINE_GUESS; // when you first click a covered square, it will be changed to MINE_GUESS
			} else if (getStatus(row, col) == MINE_GUESS) {
				visibleField[row][col] = QUESTION;   // when you click a MINE_GUESS square, it will be changed to QUESTION
			} else {
				visibleField[row][col] = COVERED;    // when you click a QUESTION square, it will be changed to COVERED
			}
		}
	}

	/**
       Uncovers this square and returns false iff you uncover a mine here.
       If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
       the neighboring area that are also not next to any mines, possibly uncovering a large region.
       Any mine-adjacent squares you reach will also be uncovered, and form 
       (possibly along with parts of the edge of the whole field) the boundary of this region.
       Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
       Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
       or a loss (opened a mine).
       
       @param row  of the square
       @param col  of the square
       @return false   iff you uncover a mine at (row, col)
       PRE: getMineField().inRange(row, col)
     */
	public boolean uncover(int row, int col) {
		if (!getMineField().hasMine(row, col)) {
			floodFill(row, col); // If there is no mine in square, search through the eight direction around square
			return true;
		} else {
			visibleField[row][col] = EXPLODED_MINE; // there is a mine in this square.You need to do the tail-in work: update some items
			for (int i = 0; i < mineField.numRows(); i++) {
				for (int j = 0; j < mineField.numCols(); j++) {
					if ((!mineField.hasMine(i, j)) && visibleField[i][j] == MINE_GUESS) {
						visibleField[i][j] = INCORRECT_GUESS;  //when u mark an area without a mine as MINE_GUESS, update it to INCORRECT_GUESS
					} else if (mineField.hasMine(i, j)
							&& (visibleField[i][j] == QUESTION || visibleField[i][j] == COVERED)) {
						visibleField[i][j] = MINE;  //when u mark an area with a mine as QUESTION or COVERED, update it to MINE
					}
				}
			}
		}
		return false;
	}

	/**
       Returns whether the game is over.
       (Note: This is not a mutator.)
       
       @return whether game over
	 */
	public boolean isGameOver() {
		int rightStatusNum = 0;
		/**
		 *  when you reach the status of MINE, INCORRECT_GUESS or EXPLODED_MINE,game over
		 *  when you are in the status of calculating the surrounding mines, 
		 *  you are in the right status and still in the game.
		 *  
		 */
		for (int i = 0; i < mineField.numRows(); i++) {
			for (int j = 0; j < mineField.numCols(); j++) {
				if (visibleField[i][j] >= MINE) {
					return true;
				}
                else if (visibleField[i][j] < MINE && visibleField[i][j] > COVERED) {
					rightStatusNum++;
				} 
			}
		}
		if (rightStatusNum == mineField.numCols() * mineField.numRows() - mineField.numMines()) {
			// the situation of winning game: your judgement of every square's status is correct
			for (int s = 0; s < mineField.numRows(); s++) {
				for (int t = 0; t < mineField.numCols(); t++) {
					if (mineField.hasMine(s, t)) {
						visibleField[s][t] = MINE_GUESS;
					} // when you won the game, all the mines should be set as MINE_GUESS.
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
       Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
       vs. any one of the covered states).
       
       @param row of the square
       @param col of the square
       @return whether the square is uncovered
       PRE: getMineField().inRange(row, col)
     */
	public boolean isUncovered(int row, int col) {
		if (visibleField[row][col] >= 0) { // Uncovered states: all non-negative values
			return true;
		} else {
			return false;
		}
	}

	// <put private methods here>
	/**
	   Recursively finding an open area with no mines. Uncover all the squares in
	   the neighboring area that are also not next to any mines, possibly uncovering
	   a large region. When you reach a square that is not in the given range or
	   uncovered or in the status of MINE_GUESS, you should stop searching. You have
	   reached the boundary. When you do the searching, every time you traverse the
	   eight position around the center one. As long as you don't reach the
	   boundary, you can continue to search with any of the eight points as the
	   center point. Otherwise,calculate the number of mines around the center one.
	  
	   @param row           of the square
	   @param col           of the square
	   @param eightPosition an 2D array which hold eight different position around a
	                        center one
	   @param a             get the x value of eight position
	   @param b             get the y value of eight position
	 */
	private void floodFill(int row, int col) {
		if (!getMineField().inRange(row, col) || isUncovered(row, col) || getStatus(row, col) == MINE_GUESS) {
			return;    // the boundary situation: stop searching
		} else if (mineField.numAdjacentMines(row, col) == 0) {
			visibleField[row][col] = 0;
			int[][] eightPosition = { { row - 1, col - 1 }, { row - 1, col }, { row - 1, col + 1 }, { row, col - 1 },
					{ row, col + 1 }, { row + 1, col - 1 }, { row + 1, col }, { row + 1, col + 1 } };
			for (int i = 0; i < eightPosition.length; i++) {
				int a = eightPosition[i][0];
				int b = eightPosition[i][1];
				floodFill(a, b); // Recursively find an open area
			}
		} else {
			visibleField[row][col] = mineField.numAdjacentMines(row, col); // calculate the number of mines around the
																			// center one
		}
	}
}
