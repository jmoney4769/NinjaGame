import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/** The second, more powerful enemy
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public class SecondEnemy extends Enemy {

	/** Constructor for SecondEnemy
	 * @param tileLocation The location of the tile
	 * @param map The dungeon in which the tile is located
	 */
	public SecondEnemy(Point tileLocation, Dungeon map) {
		
		super(tileLocation, map);
		
		name = "Monster";
		attack = 10;
		health = 30;
		
		try {
			image = ImageIO.read(new File("res/monster1.png"));
		} catch (IOException e) {
			System.err.println("SecondEnemy first image not found");
		}

	}
	
	@Override
	public void update() throws DeadCreatureException {
		
		if (health < 15)
			try {
				image = ImageIO.read(new File("res/monster2.png"));
			} catch (IOException e) {
				System.err.println("SecondEnemy second image not found");
			}
		super.update();
		super.update();
	}
}
