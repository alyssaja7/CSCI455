
/**
 * class CoinTossSimulatorTester
 * 
 * Test CoinTossSimulator class to ensure sum of the TwoHeads, TwoTails  and HeadTails
 * equals to the number of Trials performed since last reset. 
 *
 */

public class CoinTossSimulatorTester {
   public static void main(String[] args) {
      
      CoinTossSimulator simulateTest = new CoinTossSimulator();
      
      System.out.println("After constructor:");
	  System.out.println("Number of trials [exp:0]:" + simulateTest.getNumTrials());
	  System.out.println("Two-head tosses:" + simulateTest.getTwoHeads());        
	  System.out.println("Two-tail tosses:" + simulateTest.getTwoTails());
      System.out.println("One-head one-tail tosses:" + simulateTest.getHeadTails());
      System.out.println("Tosses add up correctly?" + " "
                         + (simulateTest.getTwoHeads() + simulateTest.getHeadTails() + simulateTest.getTwoTails() == simulateTest.getNumTrials()));
	  System.out.println("");

	  simulateTest.run(1);
	  System.out.println("After run(1):");
	  System.out.println("Number of trials [exp:1]:" + simulateTest.getNumTrials());
	  System.out.println("Two-head tosses:" + simulateTest.getTwoHeads());
	  System.out.println("Two-tail tosses:" + simulateTest.getTwoTails());
      System.out.println("One-head one-tail tosses:" + simulateTest.getHeadTails());
      System.out.println("Tosses add up correctly?" + " "
                         + (simulateTest.getTwoHeads() + simulateTest.getHeadTails() + simulateTest.getTwoTails() == simulateTest.getNumTrials()));
	  System.out.println("");
		
	  simulateTest.run(10);
	  System.out.println("After run(10):");
	  System.out.println("Number of trials [exp:11]:" + simulateTest.getNumTrials());
	  System.out.println("Number of trials:");
	  System.out.println("Two-head tosses:" + simulateTest.getTwoHeads());
	  System.out.println("Two-tail tosses:" + simulateTest.getTwoTails());
	  System.out.println("One-head one-tail tosses:" + simulateTest.getHeadTails());
	  System.out.println("Tosses add up correctly?" + " "
                         + (simulateTest.getTwoHeads() + simulateTest.getHeadTails() + simulateTest.getTwoTails() == simulateTest.getNumTrials()));
	  System.out.println("");
		
	  simulateTest.run(100);
	  System.out.println("After run(100):");
	  System.out.println("Number of trials [exp:111]:" + simulateTest.getNumTrials());
	  System.out.println("Two-head tosses:" + simulateTest.getTwoHeads());
	  System.out.println("Two-tail tosses:" + simulateTest.getTwoTails());
	  System.out.println("One-head one-tail tosses:" + simulateTest.getHeadTails());
	  System.out.println("Tosses add up correctly?" + " "
                         + (simulateTest.getTwoHeads() + simulateTest.getHeadTails() + simulateTest.getTwoTails() == simulateTest.getNumTrials()));
	  System.out.println("");

      simulateTest.reset();
      
	  System.out.println("After reset:");
	  System.out.println("Number of trials [exp:0]:" + simulateTest.getNumTrials());
	  System.out.println("Two-head tosses:" + simulateTest.getTwoHeads());
	  System.out.println("Two-tail tosses:" + simulateTest.getTwoTails());
	  System.out.println("One-head one-tail tosses:" + simulateTest.getHeadTails());
	  System.out.println("Tosses add up correctly?" + " "
                         + (simulateTest.getTwoHeads() + simulateTest.getHeadTails() + simulateTest.getTwoTails() == simulateTest.getNumTrials()));
	  System.out.println("");
		
	  simulateTest.run(1000);
	  System.out.println("After run(1000):");
	  System.out.println("Number of trials [exp:1000]:" + simulateTest.getNumTrials());
	  System.out.println("Two-head tosses:" + simulateTest.getTwoHeads());
	  System.out.println("Two-tail tosses:" + simulateTest.getTwoTails());
	  System.out.println("One-head one-tail tosses:" + simulateTest.getHeadTails());
	  System.out.println("Tosses add up correctly?" + " "
                         + (simulateTest.getTwoHeads() + simulateTest.getHeadTails() + simulateTest.getTwoTails() == simulateTest.getNumTrials()));
	  System.out.println("");	
	}
}
