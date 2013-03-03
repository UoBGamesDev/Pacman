import java.util.Set;


public class SetHelper
{
	public static boolean setContains (Set<Point> set, Point element)
	{
		for(Point point : set)
		{
			if(element.equals(point))
			{
				return true;
			}
		}
		
		return false;
	}
}
