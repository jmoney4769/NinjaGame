import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/** Tile that cannot have any creatures on it
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public class WallTile extends Tile {

	/** Constructor for WallTile
	 * @param tileLocation
	 * @param map
	 */
	public WallTile(Point tileLocation, Dungeon map) {
		
		super(tileLocation, map);
		
		try {
			image = ImageIO.read(new File("res/wall.jpeg"));
		} catch (IOException e) {
			System.err.println("WallTile image not found");
			System.exit(1);
		}
	}
	
	@Override
	public void update() {
		if (creature != null)
			creature = null;
	}

}
