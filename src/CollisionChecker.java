import java.util.ArrayList;
import java.util.List;

public class CollisionChecker
{
	private Main main;

	public CollisionChecker(Main main)
	{
		this.main = main;
	}

	public List<IEntity> checkCollisions(IEntity entity)
	{

		List<IEntity> entitys = new ArrayList<IEntity>();

		for (GameComponent gc : main.getGameComponents())
		{
			if (gc instanceof IEntity)
			{
				IEntity other = (IEntity) gc;

				if (entity != other)
				{
					if (entity.getCollisionMask().intersects(
							other.getCollisionMask()))
					{
						entitys.add(other);
					}
				}
			}
		}

		return entitys;
	}

	public boolean checkCollision(IEntity entity, Point position)
	{
		Rectangle rectangle = new Rectangle(position.getX(), position.getY(), 1, 1);
		
		for (GameComponent gc : main.getGameComponents())
		{
			if (gc instanceof Wall)
			{
				IEntity other = (IEntity) gc;

				if (entity != other)
				{
					if(other.getCollisionMask().intersects(rectangle))
					{
						return true;
					}
				}
			}

		}
		
		return false;
	}

}
