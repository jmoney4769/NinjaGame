import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/** The creature of which the player has control
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public class Player extends Creature {

	/** Constructor for Player
	 * @param attack The attack value for player
	 * @param tileLocation The location of the tile
	 * @param map The dungeon in which the tile is located
	 */
	public Player(int attack, Point tileLocation, Dungeon map) {
		super(tileLocation, map);
		name = "Player";
		health = 100;
		this.attack = attack;
		
		try {
			image = ImageIO.read(new File("res/player.png"));
		} catch (IOException e) {
			System.err.println("Player image not found");
			System.exit(1);
		}
	}

	/* (non-Javadoc)
	 * @see DungeonElement#update()
	 */
	@Override
	public void update() throws DeadCreatureException, WinCondition {
		if (health <= 0)
			throw new DeadCreatureException();
		
	}
}
