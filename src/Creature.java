import java.awt.Point;
import java.util.Random;

/** Abstract class representing any creature that is in the Dungeon
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public abstract class Creature extends DungeonElement {

	protected int health, attack;
	protected String name;
	
	/** Constructor for Creature
	 * @param tileLocation The location of the tile
	 * @param map The dungeon in which the tile is located
	 */
	public Creature(Point tileLocation, Dungeon map) {
		super(tileLocation, map);
		
	}
	
	/** Attacks the creature, subtracting from their health
	 * @param c The creature to attack
	 */
	public void attack(Creature c) {
		
		Random random = new Random();
		if (random.nextInt(3) <= 2) { // 75% chance
			c.setHealth(c.getHealth() - attack);
			if (c.getHealth() >= 0)
				System.out.printf("%s attacked %s and %s lost %d health\n%s's health is now %d\n", name, c.getName(), c.getName(), attack, c.getName(), c.getHealth());
		}
		else
			System.out.printf("%s attacked %s and missed\n", name, c.getName());
	}

	public boolean canMoveTo(Point destination) {
		
		if ((map.getTiles()[destination.x][destination.y] instanceof WallTile) || (map.getTiles()[destination.x][destination.y].getCreature() != null))
			return false;
		else
			return true;
	}
	
	/** Checks to see if this can attack another creature
	 * @param creature The creature to check
	 * @return True if there is one Player and one Enemy between this and creature, false otherwise
	 */
	public boolean canAttack(Creature creature, Point point) {
		
		if (((creature instanceof Player) && (this instanceof Enemy)) || ((creature instanceof Enemy) && (this instanceof Player)))
			return true;
		return false;
	}

	/** Getter for name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/** Getter for health
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/** Setter for health
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/** Setter for tileLocation
	 * @param location The location to set
	 */
	public void setTileLocation(Point location) {
		this.tileLocation = location;
	}
}
