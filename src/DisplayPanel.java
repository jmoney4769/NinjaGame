import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/** Class used to graphically show the game
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public class DisplayPanel extends JPanel {

	private Dungeon dungeon;
	
	/** Constructor for DisplayPanel
	 * @param d The dungeon to be used for the class
	 */
	public DisplayPanel(Dungeon d) {
		
		dungeon = d;
		repaint();
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				boolean b = false;
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					Point point = new Point(dungeon.getPlayer().getTileLocation().x - 1, dungeon.getPlayer().getTileLocation().y);
					movePlayer(point);
					b = true;
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					Point point = new Point(dungeon.getPlayer().getTileLocation().x + 1, dungeon.getPlayer().getTileLocation().y);
					movePlayer(point);
					b = true;
				}
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					Point point = new Point(dungeon.getPlayer().getTileLocation().x, dungeon.getPlayer().getTileLocation().y - 1);
					movePlayer(point);
					b = true;
				}
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					Point point = new Point(dungeon.getPlayer().getTileLocation().x, dungeon.getPlayer().getTileLocation().y + 1);
					movePlayer(point);
					b = true;
				}
				if (b) {

					repaint();
				}
			}
		});
		
		Timer timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dungeon.updateAll();
				repaint();						
			}
		});	
		timer.start();		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int height = getHeight() / dungeon.getTiles().length, width = getWidth() / dungeon.getTiles()[0].length;
		
		for (int i = 0; i < dungeon.getTiles().length; i++)
			for (int j = 0; j < dungeon.getTiles()[i].length; j++) {
				dungeon.getTileAtPoint(new Point(i, j)).draw(g, width, height);
			}
		
	}


	/** Attempts to move the player to the specified point
	 * @param point The point to try to move to
	 */
	private void movePlayer(Point point) {
		dungeon.attemptMove(point, dungeon.getPlayer());
		try {
			dungeon.getTileAtPoint(dungeon.getPlayer().getTileLocation()).update();
		} catch (DeadCreatureException e1) {
			JOptionPane.showMessageDialog(null, "You are dead");
			System.exit(0);
		} catch (WinCondition e1) {
			dungeon.advanceToNextLevel();
		}
		repaint();
	}
}
