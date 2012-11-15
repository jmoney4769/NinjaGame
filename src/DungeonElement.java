import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

/** Abstract class that is the parent of all elements that go into a Dungeon
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public abstract class DungeonElement {

	protected Dungeon map;
	protected BufferedImage image;
	protected Point tileLocation;

	/** Constructor for the class
	 * @param tileLocation The location at which the tile will be located
	 * @param map The Dungeon in which the tile resides
	 */
	public DungeonElement(Point tileLocation, Dungeon map) {
		
		this.tileLocation = tileLocation;
		this.map = map;
	}
	/** Update the element so that current information is used
	 * @throws DeadCreatureException Thrown if it is a Creature and it's health gets to zero
	 */
	public abstract void update() throws DeadCreatureException, WinCondition;
	
	/** Draw the element onto the panel
	 * @param g The graphics object which will be doing the drawing
	 * @param tileWidth The width of the tile
	 * @param tileHeight The height of the tile
	 */
	public void draw(Graphics g, int tileWidth, int tileHeight) {
		
		g.drawImage(image, tileLocation.x * tileWidth, tileLocation.y * tileHeight, tileWidth, tileHeight, null);
	}

	/** Getter for the tile location
	 * @return the tileLocation
	 */
	public Point getTileLocation() {
		return tileLocation;
	}
	
}
