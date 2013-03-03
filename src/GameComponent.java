import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public abstract class GameComponent
{
	public abstract void update(GameContainer gc, int delta);
	public abstract void render(GameContainer gc, Graphics g);
}
