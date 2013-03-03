
public class Rectangle
{
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Rectangle(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public int getHeight()
	{
		return height;
	}
	public int getWidth()
	{
		return width;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
	public int top()
	{
		return y;
	}
	
	public int bottom()
	{
		return y + height;
	}
	
	public int left()
	{
		return x;
	}
	
	public int right()
	{
		return x + width;
	}
	
	public boolean intersects(Rectangle rect2)
	{
		if(top() >= rect2.bottom())
		{
			return false;
		}

		if(bottom() <= rect2.top())
		{
			return false;
		}

		if(right() <= rect2.left())
		{
			return false;
		}

		if(left() >= rect2.right())
		{
			return false;
		}

		return true;


	}
	
	@Override
	public String toString()
	{
		return "X:" + x + " Y:" + y + " Width:"+ width + " Height:"+ height;
	}
	
}
