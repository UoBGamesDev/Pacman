import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MapRender extends GameComponent
{

	private TiledMap map;

	public MapRender(TiledMap map) throws SlickException
	{
		this.map = map;
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		map.render(0, 0);

	}

}
