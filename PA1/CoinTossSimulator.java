
import java.util.Random;
/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface. 
 * You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {
   
	private int twoHeads;	//record the number of TwoHeads
	private int twoTails;	//record the number of TwoTails
	private int headTails;	//record the number of HeadTails
	private int trials;		//record the number of total Trials
	private Random generator;
	
   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
      
	   twoHeads = 0;
	   twoTails = 0;
	   headTails = 0;
	   trials = 0;
	   generator = new Random();
   }

   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      
	   for(int i = 0; i < numTrials; ++i) {
          
		   int resOne = generator.nextInt(2);      //one result
		   int resAnother = generator.nextInt(2);   // the other result
		   if(resOne == 0 && resAnother == 0) {
			   ++twoHeads;
		   }else if(resOne == 1 && resAnother == 1) {
			   ++twoTails;
		   }else {
			   ++headTails;
		   }
		   ++trials;
	   }
   }
   
   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return trials; 
   }
   
   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return twoHeads;
   }
   
   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return twoTails;
   }
   
   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
	   headTails = trials - twoHeads - twoTails;     
       return headTails;
   }
   
   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
	   twoHeads = 0;
	   twoTails = 0;
	   headTails = 0;
	   trials = 0;
   }
}
