import java.awt.Point;
import java.util.Random;

/** Class that holds all of the information for the game
 * @author Jared Moore
 * @version Nov 6, 2012
 * I worked on and completed this assignment by myself
 * Extra Credit:
 * The enemies move on a timer instead of based on the player
 * Instead of ending when the first level is finished, the levels will increase every time
 */
public class Dungeon {

	private Tile[][] tiles;
	private Player player;
	private int level;
	
	/** Constructor for the Dungeon class, initializes the first level
	 */
	public Dungeon() {
		
		level = 1;
		buildMap(2);
	}	
	
	/** Builds a map of tile with the desired number of rooms and the other parameters randomized
	 * @param rooms The number of rooms to be used (will use the number for both x and y so you will get rooms^2 total)
	 */
	public void buildMap(int rooms) {
		
		char[][] map = MapGenerator.generateMap(rooms, rooms, (2 + new Random().nextInt(3)), (new Random().nextBoolean()), 'a', 'b');
		tiles = new Tile[map.length][map[0].length];
		
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++) 
				if (map[i][j] == 'a')
					tiles[i][j] = new WallTile(new Point(i, j), this);
				else if (map[i][j] == 'b') 
					tiles[i][j] = new FloorTile(new Point(i, j), this);
	
		
		Random random = new Random();
		insertPlayer: // randomly place the player
			while (true) {
				int i = random.nextInt(tiles.length - 1);
				int j = random.nextInt(tiles[i].length - 1);
				if (tiles[i][j] instanceof FloorTile) {
					player = new Player(10, new Point(i, j), this);
					tiles[i][j].setCreature(player);
					break insertPlayer;
				}
			}
		
		int temp = level; // randomly place traps based on the current level
		while (temp > 0) {
			int i = random.nextInt(tiles.length - 1);
			int j = random.nextInt(tiles[i].length);
			if (tiles[i][j] instanceof FloorTile && tiles[i][j].getCreature() == null) {
				tiles[i][j] = new TrapTile(new Point(i, j), this);
				temp--;
			}
		}
		
		insertExit: // randomly insert the player
			while (true) { 
				int i = random.nextInt(tiles.length - 1);
				int j = random.nextInt(tiles[i].length);
				if (tiles[i][j] instanceof FloorTile && tiles[i][j].getCreature() == null) {
					tiles[i][j] = new ExitTile(new Point(i, j), this);
					break insertExit;
				}
			}
		
		int first = level + 1, second = level;
		insertEnemies: // randomly insert enemies
			while (true) {
				int i = random.nextInt(tiles.length - 1);
				int j = random.nextInt(tiles[i].length);
				if ((first > 0) && (tiles[i][j] instanceof FloorTile) && (tiles[i][j].getCreature() == null)) {
					tiles[i][j].setCreature(new FirstEnemy(new Point(i, j), this));
					first--;
				}
				if ((second > 0) && (tiles[i][j] instanceof FloorTile) && (tiles[i][j].getCreature() == null)) {
					tiles[i][j].setCreature(new SecondEnemy(new Point(i, j), this));
					second--;
				}
				if ((first == 0) && (second == 0))
					break insertEnemies; 
				}
	}	

	/** Attempts to move the creature to the point, attacks if there is something there
	 * @param point The point to be moved to
	 * @param creature The creature that will be doing the moving
	 */
	public void attemptMove(Point point, Creature creature) {
		
		
		if (getTileAtPoint(point).getCreature() != null && (creature.canAttack(getTileAtPoint(point).getCreature(), point))) 
			creature.attack(getTileAtPoint(point).getCreature()); 		
			
		else if (creature.canMoveTo(point)){
			getTileAtPoint(creature.getTileLocation()).removeCreature();
			creature.setTileLocation(point);
			getTileAtPoint(point).setCreature(creature);
		}

		
	}
	
	/** Updates all of the tiles and their corresponding creatures
	 */
	public void updateAll() {
		
		//player.update();
		for (Tile[] i : tiles)
			for (Tile j : i)
				try {
					j.update();
				} catch (DeadCreatureException e) {
					j.removeCreature();
				} catch (WinCondition e) {
					advanceToNextLevel();
				}
	}	

	/** If the game is won, move to the next level 
	 */
	public void advanceToNextLevel() {
		level++;
		buildMap(level + 1);		
	}
	
	/** Getter for level
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/** Gets the tile at a certain point
	 * @param point The point in question
	 * @return The tile that is at the point
	 */
	public Tile getTileAtPoint(Point point) {
		return tiles[(int) point.getX()][(int) point.getY()];
	}
	
	/** Getter for player
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/** Getter for the tile array
	 * @return the tiles
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/** Setter for tiles
	 * @param tiles the tiles to set
	 */
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
}
