package richo.testproject.probability.coinflip.chanflip;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by Richo on 2014-04-27.
 */
class FourChanCoinFlipMain
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables
	private static final Random rand = new Random();
	private static final LongAdder numberOfDoubleTails = new LongAdder();
	private static final LongAdder numberOfFlips = new LongAdder();
	private static final LongAdder numberOfDoubleHeads = new LongAdder();
	private static final LongAdder numberOfHeadTails = new LongAdder();

	//constructors

	//methods

	public static void main(String[] args)
	{
		ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleAtFixedRate(FourChanCoinFlipMain::printInfo, 0, 5, TimeUnit.SECONDS);
		long NUMBER_OF_FLIPS = 10000000;
		for(long i = 0; i < NUMBER_OF_FLIPS; i++)
		{
			doACoinFlip();
			Thread.yield();
		}

		printInfo();
		System.out.println("Done");
		executor.shutdown();
	}

	static void doACoinFlip()
	{
		numberOfFlips.increment();
		Result firstCoin = Result.get(rand.nextInt(2));
		Result secondCoin = Result.get(rand.nextInt(2));

		//assert atleast one head
		if(firstCoin.equals(Result.TAILS) && secondCoin.equals(Result.TAILS))
		{
			//No tails, discard
			numberOfDoubleTails.increment();
			return;
		}

		if(firstCoin.equals(Result.HEAD) && secondCoin.equals(Result.HEAD))
		{
			//the desired outcome, both heads
			numberOfDoubleHeads.increment();
			return;
		}

		numberOfHeadTails.increment();
	}

	static void printInfo()
	{
		System.out.println("Number of flips: " + numberOfFlips.longValue() +
				"\nNumber of double heads(OK): " + numberOfDoubleHeads.longValue() +
				"\nNumber of head-tails(Fail): " + numberOfHeadTails.longValue() +
				"\nNumber of excluded flips(tail-tail): " + numberOfDoubleTails.longValue() +
				"\nPercentage of double heads: " + (double)numberOfDoubleHeads.longValue()/(numberOfDoubleHeads.longValue() + numberOfHeadTails.longValue()) * 100
		);
	}
}
