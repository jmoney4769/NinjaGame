import java.awt.Point;

/** Abstract class for all enemies
 * @author Jared Moore
 * @version Nov 6, 2012
 */
public abstract class Enemy extends Creature {

	/** Constructor for Enemy
	 * @param tileLocation The location of the tile
	 * @param map The dungeon in which the tile is located
	 */
	public Enemy(Point tileLocation, Dungeon map) {
		super(tileLocation, map);

	}

	@Override
	public void update() throws DeadCreatureException {
		if (health <= 0)
			throw new DeadCreatureException();
		
		boolean b = false;
		Point playerPoint = map.getPlayer().getTileLocation(), nextPoint = null;
		
		if (playerPoint.x != tileLocation.x) {
			
			if (playerPoint.x > tileLocation.x)
				nextPoint = new Point(tileLocation.x + 1,tileLocation.y);
			else 
				nextPoint = new Point(tileLocation.x - 1, tileLocation.y);
			
			if (!canMoveTo(nextPoint) && !(map.getTileAtPoint(nextPoint).getCreature() instanceof Player)) 
				b = true;			
		}
		
		if ((b) || (playerPoint.y != tileLocation.y)) {
			
			if (playerPoint.y > tileLocation.y)
				nextPoint = new Point(tileLocation.x, tileLocation.y + 1);
			else 
				nextPoint = new Point(tileLocation.x, tileLocation.y - 1);
			
			if (!canMoveTo(nextPoint) && !(map.getTileAtPoint(nextPoint).getCreature() instanceof Player))  // move somewhere 
				
				if (canMoveTo(new Point(tileLocation.x, tileLocation.y - 1)) || (map.getTileAtPoint(nextPoint).getCreature() instanceof Player))
					nextPoint = new Point(tileLocation.x, tileLocation.y - 1);
			
				else if (canMoveTo(new Point(tileLocation.x, tileLocation.y + 1)) || (map.getTileAtPoint(nextPoint).getCreature() instanceof Player))
					nextPoint = new Point(tileLocation.x, tileLocation.y + 1);
			
				else if (canMoveTo(new Point(tileLocation.x + 1, tileLocation.y))  || (map.getTileAtPoint(nextPoint).getCreature() instanceof Player))
					nextPoint = new Point(tileLocation.x + 1, tileLocation.y);
			
				else if (canMoveTo(nextPoint = new Point(tileLocation.x - 1, tileLocation.y))  || (map.getTileAtPoint(nextPoint).getCreature() instanceof Player))
					nextPoint = new Point(tileLocation.x - 1, tileLocation.y);
			
		}
		
		if (nextPoint != null) // should not happen, but could be possible if inside a square
			map.attemptMove(nextPoint, this);
	}

}
