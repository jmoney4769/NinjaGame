import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/** FloorTile that, when reached, fulfills the win condition
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public class ExitTile extends FloorTile {

	/** Constructor for ExitTile
	 * @param tileLocation The location of the tile
	 * @param map The dungeon in which the tile is located
	 */
	public ExitTile(Point tileLocation, Dungeon map) {
		
		super(tileLocation, map);
		
		try {
			image = ImageIO.read(new File("res/ladder.png"));
		} catch (IOException e) {
			System.err.println("ExitTile image not found");
			System.exit(1);
		}
	}
	
	@Override
	public void update() throws DeadCreatureException, WinCondition {
		super.update();
		if (creature instanceof Player) {
			System.out.printf("\n\nLevel %d Completed\n\n", map.getLevel());
			throw new WinCondition();
		}
	}

}
