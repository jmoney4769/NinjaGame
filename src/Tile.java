import java.awt.Graphics;
import java.awt.Point;

/** Abstract class used to represent any tile in the map
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public abstract class Tile extends DungeonElement {
	
	protected Creature creature;
	
	/** Constructor for Tile
	 * @param tileLocation The location of the tile
	 * @param map The dungeon in which the tile is located
	 */
	public Tile(Point tileLocation, Dungeon map) {
		super(tileLocation, map);
	}
	
	/** Remove the creature from the tile
	 */
	public void removeCreature() {
		creature = null;
	}

	/** Getter for Creature
	 * @return the creature
	 */
	public Creature getCreature() {
		return creature;
	}

	/** Setter for creature
	 * @param creature the creature to set
	 */
	public void setCreature(Creature creature) {
		this.creature = creature;
	}
	
	/* (non-Javadoc)
	 * @see DungeonElement#draw(java.awt.Graphics, int, int)
	 */
	@Override
	public void draw(Graphics g, int tileWidth, int tileHeight) {
		super.draw(g, tileWidth, tileHeight);
		if (creature != null)
			creature.draw(g, tileWidth, tileHeight);
	}
}
