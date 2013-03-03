
public class Point
{
	private int x;
	private int y;

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof Point)
		{
			Point otherPoint = (Point)other;
			return otherPoint.getX() == this.getX() && otherPoint.getY() == this.getY();
		}
		else
		{
			System.out.println("Unexpected Object Point");
			return super.equals(other);
		}
	}
	
	@Override
	public String toString()
	{
		return "X:" + x + " Y:" + y;
	}
	
}
