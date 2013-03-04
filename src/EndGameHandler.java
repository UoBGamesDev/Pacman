import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class EndGameHandler extends GameComponent
{

	private Main main;
	
	public EndGameHandler(Main main)
	{
		this.main = main;
	}
	
	@Override
	public void update(GameContainer gc, int delta)
	{
		boolean hasBlobs = false;
		
		for(GameComponent g : main.getGameComponents())
		{
			if(g instanceof Blob)
			{
				hasBlobs = true;
				break;
			}
		}
		
		if(!hasBlobs)
		{
			main.restart();
		}
		
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		// TODO Auto-generated method stub
		
	}

}
