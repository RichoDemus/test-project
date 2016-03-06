package richo.testproject.probability.dice.wod_dice_roller;

import java.util.concurrent.atomic.LongAdder;
import java.util.stream.LongStream;

public class DiceRoller
{
	public static void main(String[] args)
	{
		final long numberOfDice = 10000000L;
		final long successes = rollAndCalculateSuccesses(numberOfDice);;
		System.out.println("" + numberOfDice + " resulted in " + successes + " successes");
	}

	private static long rollAndCalculateSuccesses(long numberOfDice)
	{
		final LongAdder adder = new LongAdder();
		final D10 die = new D10();
		LongStream.range(0, numberOfDice).forEach(i ->
		{
			int result;
			boolean rollAgain = false;
			do
			{
				rollAgain = false;
				result = die.roll();
				if(result >= 8)
				{
					adder.increment();
				}
				if(result == 0)
				{
					adder.increment();
					rollAgain = true;
				}
			} while(rollAgain);

		});

		return adder.sum();
	}
}
