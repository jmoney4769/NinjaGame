import java.awt.Dimension;

import javax.swing.JFrame;

/** Class used to run all of the methods
 * @author Jared Moore
 * @versoin Nov 4, 2012
 */
public class HW9Main {

	/** Main methods, run the program
	 * @param args Unused
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		DisplayPanel displayPanel = new DisplayPanel(new Dungeon());
		frame.add(displayPanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(500, 500));
	}
	
}
