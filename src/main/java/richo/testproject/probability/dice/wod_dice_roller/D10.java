package richo.testproject.probability.dice.wod_dice_roller;

import java.util.Random;

public class D10
{
	private final Random rand = new Random();

	public int roll()
	{
		return rand.nextInt(10);
	}
}
