package richo.testproject.jms.singleprocess;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang.time.StopWatch;

import java.util.concurrent.*;

/**
 * Created by Richo on 2014-04-16.
 */
public class JmsStuff
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	private static ScheduledExecutorService timer =
			new ScheduledThreadPoolExecutor(10, new ThreadFactoryBuilder().setDaemon(false).setNameFormat("Timer-%s").build());

	private static final ExecutorService executor =
			Executors.newCachedThreadPool(new ThreadFactoryBuilder().setDaemon(false).setNameFormat("Workers-%s").build());

	//constructors

	//methods

	public static void main(String[] args) throws InterruptedException
	{
		final StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		final int numberOfMessagesToSend = 1000000;
		final int numberOfSenders = 1;
		System.out.println("Start sending " + numberOfMessagesToSend + " messages");
		final JMSMessageConsumer consumer = new JMSMessageConsumer();

		timer.scheduleAtFixedRate(new InfoTask(), 0, 1, TimeUnit.SECONDS);

		for (int i = 0; i < numberOfSenders; i++)
		{
			executor.execute(new JMSMessageProducer("Hello World!", numberOfMessagesToSend / numberOfSenders));
		}

		executor.execute(consumer);

		System.out.println("Waiting for all messages to be sent");
		while (Counters.numberOfSentMessages.get() < numberOfMessagesToSend)
		{
			Thread.sleep(100);
		}
		stopwatch.split();
		System.out.println("Waiting for all messags to be received");
		while (Counters.numberOfSentMessages.get() > Counters.numberOfReceivedMessages.get())
		{
			Thread.sleep(100);
		}
		stopwatch.stop();
		System.out.println("Done, shutting down");
		executor.execute(new JMSMessageProducer(JMSMessageConsumer.KEYWORD_SHUTDOWN));
		executor.shutdown();
		timer.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);
		printInfo(stopwatch);
	}

	private static void printInfo(StopWatch stopwatch)
	{
		System.out.println("Number of sent messages: " + Counters.numberOfSentMessages.get());
		System.out.println("Number of received messages: " + Counters.numberOfReceivedMessages.get());
		System.out.println("Number of receives: " + Counters.numberOfReceives.get());

		System.out.println("Time spent: " + stopwatch.toString());
	}
}
