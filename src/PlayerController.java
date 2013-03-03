import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;


public class PlayerController implements ICharacterController
{

	private GameContainer gc;
	
	public PlayerController(GameContainer gc)
	{
		this.gc = gc;
	}
	
	@Override
	public Point getNextPoint(Character character)
	{
		Point currentPosition = character.getPosition();
		if(gc.getInput().isKeyDown(Input.KEY_UP))
		{
			return new Point(currentPosition.getX(), currentPosition.getY() - 1);
		}
		
		if(gc.getInput().isKeyDown(Input.KEY_DOWN))
		{
			return new Point(currentPosition.getX(), currentPosition.getY() + 1);
		}
		
		if(gc.getInput().isKeyDown(Input.KEY_LEFT))
		{
			return new Point(currentPosition.getX() - 1, currentPosition.getY());
		}
		
		if(gc.getInput().isKeyDown(Input.KEY_RIGHT))
		{
			return new Point(currentPosition.getX() + 1, currentPosition.getY());
		}
		
		return new Point(currentPosition.getX(), currentPosition.getY());
		
		
	}

}
