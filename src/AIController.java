import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public abstract class AIController implements ICharacterController
{
	
	private Point currentTargetTile;
	private Stack<Point> points = new Stack<Point>();
	private Point goal = null;
	private CollisionChecker collisionChecker;
	
	private static int lastUpdate = 0;

	private static HashSet<Point>includedInPath = new HashSet<Point>();
	


	public AIController(CollisionChecker collisionChecker)
	{
		this.collisionChecker = collisionChecker;
	}

	@Override
	public Point getNextPoint(Character character)
	{
		int update = Main.getUpdate();
		
		if(update != lastUpdate)
		{
			includedInPath.clear();
			lastUpdate = update;
		}
		
		currentTargetTile = getTargetTile();

		if (!currentTargetTile.equals(goal))
		{
			points = calculatePath(character, currentTargetTile);
			goal = currentTargetTile;
		}
		
		includedInPath.addAll(points);

		if (points.size() > 0 && !collisionChecker.checkCollision(character, points.peek()))
		{
			return points.pop();
		}
		else
		{
			return character.getPosition();
		}
	}

	public abstract Point getTargetTile();

	public Stack<Point> calculatePath(Character character, Point goal)
	{
		Node initalNode = new Node(character.getPosition(), null, goal);
		PriorityQueue<Node> openSet = new PriorityQueue<Node>(30,
				new Comparator<Node>()
				{

					@Override
					public int compare(Node o1, Node o2)
					{
						if (o1.getFValue() > o2.getFValue())
						{
							return 1;
						}
						else if (o1.getFValue() < o2.getFValue())
						{
							return -1;
						}
						else
						{
							return 0;
						}
					}
				});

		openSet.add(initalNode);

		Node currentNode;

		Set<Point> closedSet = new HashSet<Point>();

		while (!((currentNode = openSet.poll()).getPosition().equals(goal)))
		{
			closedSet.add(currentNode.getPosition());
			openSet.addAll(expandNode(currentNode, openSet, closedSet, goal,
					character));
		}

		Stack<Point> path = new Stack<Point>();
		while (currentNode.getParent() != null)
		{
			path.add(currentNode.getPosition());
			currentNode = currentNode.getParent();
		}

		return path;
	}

	public List<Node> expandNode(Node node, Collection<Node> openSet,
			Set<Point> closedSet, Point goal, Character character)
	{
		List<Node> newNodes = new ArrayList<Node>();

		newNodes.add(new Node(new Point(node.getPosition().getX() + 1,
				node.getPosition().getY()), node, goal));
		newNodes.add(new Node(new Point(node.getPosition().getX() - 1,
				node.getPosition().getY()), node, goal));
		newNodes.add(new Node(new Point(node.getPosition().getX(),
				node.getPosition().getY() + 1), node, goal));
		newNodes.add(new Node(new Point(node.getPosition().getX(),
				node.getPosition().getY() - 1), node, goal));

		for (int i = 0; i < newNodes.size(); i++)
		{
			if (openSet.contains(newNodes.get(i))
					|| SetHelper.setContains(closedSet,
							newNodes.get(i).getPosition()))
			{
				newNodes.remove(i);
				i--;
			}

			else if (collisionChecker.checkCollision(character,
					newNodes.get(i).getPosition()))
			{
				newNodes.remove(i);
				i--;

			}
		}

		return newNodes;
	}
	
	public static HashSet<Point> getIncludedInPath()
	{
		return includedInPath;
	}
}
