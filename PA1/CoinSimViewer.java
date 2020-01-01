
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * class CoinSimViewer
 * 
 * Prompts for the number of trials， and do error checking.
 * Get the results of tossing a coin for "the number of trials". 
 * Draw the frame and three bars.
 * 
 * @param trials the total trials which user enters
 */

public class CoinSimViewer {
	public static void main(String[] args) {
       
		Scanner in = new Scanner(System.in);      // scan the user input
		System.out.println("Enter number of trials: ");
		
		int trials = in.nextInt();	
		while(trials <= 0) {
           //error checking
			System.out.println("ERROR: Number entered must be greater than 0.");
			System.out.println("Enter number of trials: ");
			trials = in.nextInt();
		}
		JFrame frame = new JFrame();
		frame.setSize(800, 500);
		frame.setTitle("CoinSim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CoinSimComponent coinSimComponent = new CoinSimComponent(trials);
		frame.add(coinSimComponent);
		frame.setVisible(true);
		in.close();
	}
}
