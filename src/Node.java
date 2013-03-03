
public class Node
{
	private Point position;
	private Node parent;
	private int gValue;
	private int hValue;
	private int fValue;
	
	public Node(Point position, Node parent, Point goal)
	{
		if(parent == null)
		{
			gValue = 0;
		}
		else
		{
			gValue = parent.getGValue() + 1;
		}
		
		hValue = manhattanDistance(position, goal);
		
		fValue = hValue + gValue;
		
		if(SetHelper.setContains(AIController.getIncludedInPath(), position))
		{
			fValue += 20;
		}
		
		
		
		this.position = position;
		this.parent = parent;
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public Node getParent()
	{
		return parent;
	}
	
	public int getGValue()
	{
		return gValue;
	}
	
	public int getFValue()
	{
		return fValue;
	}
	
	public int manhattanDistance(Point a, Point b)
	{	
		int xDifference = Math.abs(a.getX() - b.getX());
		int yDifference = Math.abs(a.getY() - b.getY());
		return xDifference + yDifference;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof Node)
		{
			Node otherNode = (Node)other;
			return position.equals(otherNode.getPosition());
		}
		else
		{
			System.out.println("Unexpected Object Node");
			return super.equals(other);
		}
	}
	
}
