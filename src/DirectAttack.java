
public class DirectAttack extends AIController
{

	private Character player;
	
	public DirectAttack(Character player, CollisionChecker collisionChecker)
	{
		super(collisionChecker);
		this.player = player;
	}
	
	@Override
	public Point getTargetTile()
	{
		return player.getPosition();
	}

}
