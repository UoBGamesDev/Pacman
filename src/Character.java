import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Character extends GameComponent implements IEntity
{
	private Image image;
	private Point position;
	private ICharacterController controller;
	private int updateSpeed;
	private int untilUpdate = 0;
	private CollisionChecker collisionChecker;
	private Direction directionOfMovement = Direction.NONE;
	private boolean player;
	private Points points;
	private Main main;

	public Character(Point position, ICharacterController controller,
			CollisionChecker collisionChecker, int updateSpeed, Image image,
			boolean player, Main main) throws SlickException
	{
		this.player = player;
		this.updateSpeed = updateSpeed;
		this.position = position;
		this.controller = controller;
		this.collisionChecker = collisionChecker;
		this.image = image;
		this.main = main;

		if (player)
		{
			this.points = new Points();
			main.addGameComponent(points);
		}

	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		untilUpdate -= delta;
		if (untilUpdate <= 0)
		{
			Point point = controller.getNextPoint(this);

			Point oldPosition = new Point(position.getX(), position.getY());

			position = point;

			List<IEntity> collisions = collisionChecker.checkCollisions(this);

			if (collisions.size() > 0)
			{
				for (IEntity entity : collisions)
				{
					if (entity instanceof Character)
					{
						Character character = (Character) entity;
						if (character.isPlayer())
						{
							main.restart();
						}

						position = oldPosition;
					}
					else if (entity instanceof Wall)
					{
						position = oldPosition;
					}
					else if (entity instanceof Blob)
					{
						if (player)
						{
							points.addPoints(10);
							((Blob) entity).destroy();
						}
					}
				}
			}

			if (position.getX() == oldPosition.getX()
					&& position.getY() == oldPosition.getY())
			{
				directionOfMovement = Direction.NONE;
			}
			else if (position.getX() > oldPosition.getX())
			{
				directionOfMovement = Direction.RIGHT;
			}
			else if (position.getX() < oldPosition.getX())
			{
				directionOfMovement = Direction.LEFT;
			}
			else if (position.getY() > oldPosition.getY())
			{
				directionOfMovement = Direction.DOWN;
			}
			else if (position.getY() < oldPosition.getY())
			{
				directionOfMovement = Direction.UP;
			}

			untilUpdate = updateSpeed;

		}

	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		image.draw(position.getX() * Main.TileSize, position.getY()
				* Main.TileSize);

	}

	@Override
	public Rectangle getCollisionMask()
	{
		return new Rectangle(position.getX(), position.getY(), 1, 1);
	}

	public Point getPosition()
	{
		return position;
	}

	public Direction getDirectionOfMovement()
	{
		return directionOfMovement;
	}

	public boolean isPlayer()
	{
		return player;
	}

}
