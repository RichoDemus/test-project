package richo.testproject.probability.dice.wod_dice_roller;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

import static org.junit.Assert.fail;

public class D10Test
{
	@Test
	public void testRoll() throws Exception
	{
		final Map<Integer, LongAdder> results = new HashMap<>();
		final D10 target = new D10();

		IntStream.range(0, 1000000).forEach(i ->
		{
			final int result = target.roll();
			if (result > 9)
			{
				fail("Rolled above 9");
			}
			if (result < 0)
			{
				fail("Rolled below 0");
			}
			results.computeIfAbsent(result, integer -> new LongAdder()).increment();
		});

		results.forEach((k,v) -> System.out.println("Rolled " + v.sum() + " " + k + "s"));

	}
}