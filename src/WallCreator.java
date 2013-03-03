import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.tiled.TiledMap;


public class WallCreator
{ 
	private static final int COLLISION_LAYER = 1;
	
	public static void loadWalls(Main main,TiledMap map)
	{
		for(int i = 0; i < map.getObjectCount(1); i++)
		{
			int collisionX = map.getObjectX(COLLISION_LAYER, i);
			int collisionY = map.getObjectY(COLLISION_LAYER, i);
			int collisionWidth = map.getObjectWidth(COLLISION_LAYER, i);
			int collisionHeight = map.getObjectHeight(COLLISION_LAYER, i);
			
			main.addGameComponent(new Wall(collisionX, collisionY, collisionWidth, collisionHeight));
		}
	}
	
	
}
