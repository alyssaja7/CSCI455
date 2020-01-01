
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 * class CoinSimComponent
 * 
 * Do multiple Trials, get the data result of three bars,
 * like the number of twoHeads, towTails, headTails and total trials.
 * By using these data to calculate the percentage of three different results.
 * Based on the information we have gotten and caculated, draw three bars and 
 * show their related data in the picture.
 * 
 */
public class CoinSimComponent extends JComponent {
	
	private final int WIDTH_OF_BAR = 60;
	private final int REAL_BOTTOM = 50;  
	
	private double scale;
	private int bottomOfBar;
	
	private int twoHeads;
	private int twoTails;
	private int headTails;
	private int trials;
	
	private int perTwoHeads;
	private int perTwoTails;
	private int perHeadTails;
	
	Color TWO_HEADS_COLOR = Color.RED;
	Color HEAD_TAIL_COLOR = Color.GREEN;
	Color TWO_TAILS_COLOR = Color.BLUE;
	
	public CoinSimComponent(int numOfTrials){
       
        CoinTossSimulator coinTossSimulator = new CoinTossSimulator();
		coinTossSimulator.run(numOfTrials);       
		twoHeads = coinTossSimulator.getTwoHeads();
		twoTails = coinTossSimulator.getTwoTails();
		headTails = coinTossSimulator.getHeadTails();
		trials = coinTossSimulator.getNumTrials();
		
		perTwoHeads = (int)Math.round((double)twoHeads / trials * 100);      //avoid causing precision problem
		perTwoTails = (int)Math.round((double)twoTails / trials * 100);
		perHeadTails = (int)Math.round((double)headTails / trials * 100);
	}
   
   /**
      According to the data we got, draw the components(bar Of TwoHeads, 
      bar Of TwoTails, bar Of HeadTails) of the result statistic picture.
    */
    public void paintComponent(Graphics g){
       
        Graphics2D g2 = (Graphics2D) g;	
		Font font = g2.getFont();

		scale = ((double)(getHeight() - 2 * REAL_BOTTOM - font.getSize()) / trials); //according to the only one result which is 100%, calculate the sacle
		bottomOfBar = getHeight() - REAL_BOTTOM;
	       
	    Bar barTwoHeads = new Bar(bottomOfBar, getWidth() / 4 - WIDTH_OF_BAR / 2, WIDTH_OF_BAR, twoHeads, scale, TWO_HEADS_COLOR, "Two Heads:" + " " + twoHeads + " " + "(" + perTwoHeads + "%" + ")");
		barTwoHeads.draw(g2);
	       
	    Bar barHeadTails = new Bar(bottomOfBar, getWidth() / 4 * 2 - WIDTH_OF_BAR / 2, WIDTH_OF_BAR, headTails, scale, HEAD_TAIL_COLOR, "A Head and A Tail:" + " " + headTails + " " + "(" + perHeadTails + "%" + ")");
		barHeadTails.draw(g2);
	       
	    Bar barTwoTails = new Bar(bottomOfBar, getWidth() / 4 * 3 - WIDTH_OF_BAR / 2, WIDTH_OF_BAR, twoTails, scale, TWO_TAILS_COLOR, "Two Tails:" + " " + twoTails + " " + "(" + perTwoTails + "%" + ")");
	    barTwoTails.draw(g2);	
	   }
}
