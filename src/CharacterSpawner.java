import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class CharacterSpawner
{
	final static int SPAWNS_LAYER = 0;
	final static int ENEMY_COUNT  = 4;
	
	public static void spawnCharacters(Main main, CollisionChecker collisionChecker, TiledMap map) throws SlickException
	{
		Rectangle playerPosition = getPosition(map, SPAWNS_LAYER, "PlayerSpawn");
		Rectangle enemyPosition  = getPosition(map, SPAWNS_LAYER,  "EnemySpawn");
		
		Character player = new Character(randomPosition(playerPosition), new PlayerController(Main.getGc()), collisionChecker, 200, new Image("content/pacLeft.png"), true, main);
		main.addGameComponent(player);
		
		List<Point>enemyPositions = new ArrayList<Point>();
		
		for(int i = 0; i < ENEMY_COUNT; i++)
		{
			boolean added = false;
			while(!added)
			{
				Point randomPoint = randomPosition(enemyPosition);
				
				if(!enemyPositions.contains(randomPoint))
				{
					enemyPositions.add(randomPoint);
					added = true;
				}
			}
		}
		
		for(Point point : enemyPositions)
		{
			main.addGameComponent(new Character(point, new DirectAttack(player, collisionChecker), collisionChecker, 600, new Image("content/ghost.png"), false, main));
		}
		
		
		
	}
	
	private static Rectangle getPosition(TiledMap map, int layer, String name) throws ObjectNotFound
	{
		for(int i=0; i < map.getObjectCount(layer); i++)
		{
			if(map.getObjectName(layer, i).equals(name))
			{
				return new Rectangle(map.getObjectX(layer, i), map.getObjectY(layer, i), map.getObjectWidth(layer, i), map.getObjectHeight(layer, i));
			}
		}
		
		throw new ObjectNotFound(name, layer);
	}
	
	public static Point randomPosition(Rectangle rectangle)
	{
		int x = RandomHelper.numberBetween(rectangle.left(), rectangle.right())  / Main.TileSize;
		int y = RandomHelper.numberBetween(rectangle.top(), rectangle.bottom()) / Main.TileSize;
		return new Point(x,y);
	}
	
	public static class ObjectNotFound extends SlickException
	{
		public ObjectNotFound(String objectName, int layer)
		{
			super("Could not find " + objectName + "on layer " + layer);
		}
	}
}
