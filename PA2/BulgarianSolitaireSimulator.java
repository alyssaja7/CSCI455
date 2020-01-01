

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
   First determine which mode we will use. 
   There are two choice: single step and userMode.
   Based on the mode we choose, create and initialize the SolitaireBoard.
   Under the coresponding mode, play the game.
 
   @param singleStep: single step mode
   @param userMode: the mode which data provided by the user
*/

public class BulgarianSolitaireSimulator {

   public static void main(String[] args) {
      
      boolean singleStep = false;
      boolean userMode = false;

      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userMode = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }

      // <add code here>
      Scanner in = new Scanner(System.in);
      SolitaireBoard solitaireBoard = makeSolitaireBoard(userMode, in);
      run(solitaireBoard, singleStep, in);
   }
   
   // <add private static methods here>
   /**
      According to the selected mode, create and Initialize the solitaire board.
      There are two different models in this game:
      user model and single step model.
     
      @param userMode: the user model for solitaire board game which data is provided by the user
      @param configList: the list of elements we got for the solitaire board
      @param in: input which reads from the console
      
      @return solitaireBoard under one specific game mode
   */
   
   private static SolitaireBoard makeSolitaireBoard(Boolean userMode, Scanner in) {
      
	   if(!userMode) {
		   return new SolitaireBoard();
	   }else {
          printInitial();
		  List<Integer> configList = new ArrayList<Integer>();
		  return userMode(configList, in); 
	   }
   }
   
   /**
      Run the whole game until the solitaire board get the result of 1-9 in any order.
      Print the information of every result during the game and print the final result out.
    
      @param solitaireBoard: the object of solitaire board
      @param singleStep: single step model
      @param in: input which reads from the console
   */
   private static void run(SolitaireBoard solitaireBoard, Boolean singleStep, Scanner in) {
      
	   int times = 0;
	   System.out.println("Initial configuration: " + solitaireBoard.configString());
	   while(!solitaireBoard.isDone()) {
		   solitaireBoard.playRound();
		   ++times;
		   System.out.println("[" + times + "] Current configuration: " + solitaireBoard.configString());
		   if(singleStep) {
			   System.out.print("<Type return to continue>");
			   in.nextLine();
		   }   
	   }
	   System.out.println("Done!");  
   }
   
   /**
      Create the SolitaireBoard under the user game mode.
      Because of input from the user typing, first we need to determine 
      whether the data that user input is valid.
      Data that user inputs should satisfy these following conditions: 
      	1. There can be some spaces before, between or after every element: use regex to determine it
     	2. Element can not be Zero.
     	3. The sum of all elements should be CARD_TOTAL
        
      @param flag: to indicate if the data we got and the solitaire board we create are valid
      @param configList: the list of elements we got for the solitaire board
      @param in: input which reads from the console
     
      @return SolitaireBoard created under user mode
   */
   
   private static SolitaireBoard userMode(List<Integer> configList, Scanner in){
	   boolean flag;
	   int sum;
	   while(true) {
		   flag = false;
		   sum = 0;
		   String config = in.nextLine();
		   if(config.matches("^[\\s0-9]+$")) {
			   for(String element: config.split("\\s")) {		
				   if("".equals(element)) {
					   continue;		//check if there are some spaces in the input. If yes, skip it.
				   }else if(Integer.parseInt(element) == 0) {
					 //if there are some zeroes in the input, it indicates this input list in invalid. 
					 //We clear the whole arrayList and set it as invalid input
					   configList.clear();	
					   flag = false;	
					   break;
				   }else {
					   int num = Integer.parseInt(element);
					   sum += num;
					   configList.add(num);
					   flag = true;
				   }
			   }
			   //To determine if the sum of all element equals to CRAD_TOTAL
			   if(sum != SolitaireBoard.CARD_TOTAL) {
				   configList.clear();
				   flag = false;
			   }
			   if(flag) {
				   break;
			   }else {
				   printError();
			   }
			   
		   }else {
			   printError();
		   }   
	   }
	   return new SolitaireBoard((ArrayList<Integer>)configList); 
   }
   
   /*
     Print the hint at the beginning of the game
   */
   private static void printInitial() {
      
	   System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
	   System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
	   printInput();
   }
   
   /*
     Print the error message
   */
   private static void printError() {
      
	   System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " + SolitaireBoard.CARD_TOTAL);
       printInput();
   }
   
   /*
     Remind users to type data
   */
   private static void printInput() {
      
	   System.out.println("Please enter a space-separated list of positive integers followed by newline:");
   }
  
}
