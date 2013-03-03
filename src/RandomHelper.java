import java.util.Random;


public class RandomHelper
{
	private static Random random = new Random();
	
	public static int numberBetween(int lower, int upper)
	{
		int difference = upper - lower;
		return random.nextInt(difference + 1) + lower;
	}
}
