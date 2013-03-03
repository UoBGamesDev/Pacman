import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class BlobSpawner
{
	private static final int BLOB_LAYER = 2;
	private static final int BLOB_COUNT = 200;
	
	public static void addBlobs(TiledMap map, Main main) throws SlickException
	{	
		int i = 0;
		
		//Get Object Count returns a maximum of 32 for some reason
		while(map.getObjectName(BLOB_LAYER, i) != null)
		{
			int blobX = map.getObjectX(BLOB_LAYER, i);
			int blobY = map.getObjectY(BLOB_LAYER, i);
			main.addGameComponent(new Blob(main, blobX, blobY));
			i++;
		}
	}
}
