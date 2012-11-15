import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/** Tile on which a creature can be
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public class FloorTile extends Tile {

	/** Constructor for FloorTile
	 * @param tileLocation The location of the tile
	 * @param map The dungeon in which the tile is located
	 */
	public FloorTile(Point tileLocation, Dungeon map) {
		
		super(tileLocation, map);
		try {
			image = ImageIO.read(new File("res/floor.jpeg"));
		} catch (IOException e) {
			System.err.println("FloorTile image not found");
			System.exit(1);
		}
	}
	
	@Override
	public void update() throws DeadCreatureException, WinCondition {
		
		if (creature != null)
			creature.update();
	}

}
