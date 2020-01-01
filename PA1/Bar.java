
// we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {
	
	private int bottomOfBar;
	private int leftOfBar;
	private int widthOfBar;
	private int numberOfBar; 
	private double scaleOfBar;
	private Color colorOfBar;
	private String labelOfBar;
	private int realHeightOfBar;  
	Color LABEL_COLOR = Color.BLACK;

   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param barHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int barHeight,
              double scale, Color color, String label) {
      
	   bottomOfBar = bottom;  
	   leftOfBar = left;
	   widthOfBar = width;
	   numberOfBar = barHeight;  
	   scaleOfBar = scale;
	   colorOfBar = color;
	   labelOfBar = label;
	   realHeightOfBar = (int)Math.round(numberOfBar * scaleOfBar);  //avoid causing pricision problem     
   }
   
   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
      
	   Font font = g2.getFont();
	   FontRenderContext context = g2.getFontRenderContext();
	   Rectangle2D labelOfBarBounds = font.getStringBounds(labelOfBar, context);
	   int widthOfLabel = (int)labelOfBarBounds.getWidth();
	   int heightOfLabel = (int)labelOfBarBounds.getHeight();
	   Rectangle rect = new Rectangle(leftOfBar, bottomOfBar - heightOfLabel - realHeightOfBar, widthOfBar, realHeightOfBar);
	   
	   g2.setColor(colorOfBar);
	   g2.fill(rect);
	   g2.setColor(LABEL_COLOR);  
	   g2.drawString(labelOfBar, (int)(leftOfBar + widthOfBar / 2 - widthOfLabel / 2), bottomOfBar);
   }
}
