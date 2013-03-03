import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Blob extends GameComponent implements IEntity
{
	private int x;
	private int y;
	private Image blob;
	private Main main;

	public Blob(Main main, int x, int y) throws SlickException
	{
		blob = new Image("content/blob.png");
		this.x = x / Main.TileSize;
		this.y = y / Main.TileSize;
		this.main = main;
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		blob.draw(x * Main.TileSize, y * Main.TileSize);

	}

	@Override
	public Rectangle getCollisionMask()
	{
		return new Rectangle(x, y, 1, 1);
	}
	
	public void destroy()
	{
		main.removeComponent(this);
	}

}
