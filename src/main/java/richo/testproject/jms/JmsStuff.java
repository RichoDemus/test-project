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
		executor.execute(new JMSMessageProducer());
		executor.execute(new JMSMessageConsumer());
		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);
	}
}
