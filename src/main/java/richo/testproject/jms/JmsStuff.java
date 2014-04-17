package richo.testproject.jms;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Richo on 2014-04-16.
 */
public class JmsStuff
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	private static ExecutorService executor =
			Executors.newCachedThreadPool(new ThreadFactoryBuilder().setDaemon(false).setNameFormat("Executor-%s").build());

	//constructors

	//methods

	public static void main(String[] args) throws InterruptedException
	{
		final int numberOfMessagesToSend = 100;
		JMSMessageConsumer consumer = new JMSMessageConsumer();

		executor.execute(new JMSMessageProducer("Hello World!", numberOfMessagesToSend));

		executor.execute(consumer);
		//Thread.sleep(5000);

		System.out.println("Waiting for all messages to be sent");
		while(Counters.numberOfSentMessages.get() < numberOfMessagesToSend)
		{
			Thread.sleep(100);
		}
		System.out.println("Waiting for all messags to be received");
		while(Counters.numberOfSentMessages.get() > Counters.numberOfReceivedMessages.get())
		{
			Thread.sleep(100);
		}
		System.out.println("Done, shutting down");
		executor.execute(new JMSMessageProducer(JMSMessageConsumer.KEYWORD_SHUTDOWN));
		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);

		printInfo();
	}

	private static void printInfo()
	{
		System.out.println("Number of sent messages: " + Counters.numberOfSentMessages.get());
		System.out.println("Number of received messages: " + Counters.numberOfReceivedMessages.get());
		System.out.println("Number of receives: " + Counters.numberOfReceives.get());
	}
}
