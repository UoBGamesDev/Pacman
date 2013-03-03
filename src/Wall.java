import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Wall extends GameComponent implements IEntity
{

	private Rectangle position;
	
	public Wall(int x, int y, int width, int height)
	{
		position = new Rectangle(x / Main.TileSize, y / Main.TileSize, width / Main.TileSize, height / Main.TileSize);
	}
	
	@Override
	public Rectangle getCollisionMask()
	{
		return position;
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		// TODO Auto-generated method stub
		
	}

}
