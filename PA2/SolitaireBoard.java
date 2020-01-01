
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total 
  number of cards is for the game by changing NUM_FINAL_PILES, below.  
  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.  (See comments 
  below next to named constant declarations for more details on this.)
  
  @param piles: partially filled array, used to store the number of each pile
  @param pilesNum: represents how much piles in the piles Now. In other words, it's the currentSize of the piles array.
*/


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assgt 
   // description for details.
  
   
   /**
      Representation invariant:
      
      	   pilesNum should be not smaller than 0 and not greater than CARD_TOTAL.
           Every element in piles should be between 0(exclusive) and CARD_TOTAL(inclusive).
           The sum of each element in piles should be CARD_TOTAL.

   */
   
   // <add instance variables here>
  
   private int[] piles = new int[CARD_TOTAL + 1];	 
   private int pilesNum;					
   private Random generator;
   
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of 
      cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to 
      SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {
	   pilesNum = piles.size();
	   for(int i = 0; i < pilesNum; ++i) {
		   this.piles[i] = piles.get(i);
	   }
	   
      // sample assert statement (you will be adding more of these calls)
      // this statement stays at the end of the constructor.
      assert isValidSolitaireBoard();   
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
	   generator = new Random();
	   pilesNum = 0;
	   int upperBound = CARD_TOTAL;
	  
	   while(upperBound > 0) {
		   piles[pilesNum] = generator.nextInt(upperBound) + 1;
		   upperBound -= piles[pilesNum];
		   pilesNum++;
	   }
	   assert isValidSolitaireBoard();   
   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration 
      according to the rules of Bulgarian solitaire: Takes one card from each
      pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */
   public void playRound() {
	   int newPiles = 0;
	   //play one Round game
	   for(int i = 0; i < pilesNum; ++i) {
		   piles[i]--;
		   newPiles++;
	   }
	   piles[pilesNum] = newPiles;
	   pilesNum += 1;		//update the currentSize of piles	
	   
	   //remove 0 from the piles
	   int offset = 0;
	   for(int i = 0; i < pilesNum; ++i) {
		   if(piles[i] == 0) {
			   ++offset;
			   continue;
		   }
		   piles[i - offset] = piles[i]; 
	   }
	   pilesNum -= offset;	//update the currentSize of piles	
	   
	   /**
          deal with the remaining element in piles after removing all 0 
          and shifting all non-zero elements, if there are some zeros 
          in piles(which means offset != 0).
       */
	   if(offset != 0 ) {
		   int count = 0;
		   for(int i = pilesNum; ; ++i) {
			   piles[i] = 0;
			   ++count;
			   if(count == offset) {
				   break;
			   }
		   }   
	   }
	   assert isValidSolitaireBoard();   
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, 
      there are NUM_FINAL_PILES piles that are of sizes 
      1, 2, 3, . . . , NUM_FINAL_PILES, 
      in any order.
   */
   
   public boolean isDone() {
	   boolean flag = true;
	   boolean[] flags = new boolean[CARD_TOTAL + 1];
	   for(int i = 0; i < pilesNum; ++i) {
		   flags[piles[i]] = true;
	   }
	   for(int i = 1; i <= NUM_FINAL_PILES; ++i) {
		   if(flags[i] == false) {
			   flag = false;
		   }
	   }
	   assert isValidSolitaireBoard(); 
       return flag;  
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
   */
   public String configString() {
	   String config = "";
	   for(int pile: piles) {
		   if(pile != 0) {
			   config += pile + " ";	//piles is partially filled array, we don't need to print the remaining zero part.
		   }
	   }
	   assert isValidSolitaireBoard(); 
       return config.trim();  
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard() {
	   int sum = 0;
	   for(int pile: piles) {
		   if(pile < 0 || pile > CARD_TOTAL) {
			   return false;
		   }
		   sum += pile;
	   }
	   if(sum == CARD_TOTAL) {
		   return true;
	   }else {
		   return false;  
	   }
   }

   // <add any additional private methods here>
  
}
