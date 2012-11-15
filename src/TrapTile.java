import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/** FloorTile that will lower the health of a creature that steps on it
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public class TrapTile extends FloorTile {

	/** Constructor for TrapTile
	 * @param tileLocation
	 * @param map
	 */
	public TrapTile(Point tileLocation, Dungeon map) {
		super(tileLocation, map);
		
		try {
			image = ImageIO.read(new File("res/trap.png"));
		} catch (IOException e) {
			System.err.println("FloorTile image not found");
			System.exit(1);
		}  
	}
	
	/* (non-Javadoc)
	 * @see FloorTile#update()
	 */
	@Override
	public void update() throws DeadCreatureException, WinCondition {
		super.update();
		if (getCreature() != null) {
			getCreature().setHealth(getCreature().getHealth() - 5);
			System.out.printf("%s is on a trap tile and lost 5 health\nIt's health is now %d", 
					getCreature().getName(), getCreature().getHealth());
		}
	}

}
