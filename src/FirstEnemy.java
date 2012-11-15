import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/** The first, weaker enemy
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public class FirstEnemy extends Enemy {

	/** Constructor for FirstEnemy
	 * @param tileLocation The location of the tile
	 * @param map The dungeon in which the tile is located
	 */
	public FirstEnemy(Point tileLocation, Dungeon map) {
		
		super(tileLocation, map);
		
		name = "Ninja Enemy";
		attack = 5;
		health = 20;
		
		try {
			image = ImageIO.read(new File("res/enemy.png"));
		} catch (IOException e) {
			System.err.println("FirstEnemy image not fount");
		}
	}
}
