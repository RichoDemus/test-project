package richo.testproject.condition;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Richo on 2014-05-23.
 */
public class ConditionTest
{
	@Test
	public void testCondition() throws Exception
	{
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		final Lock lock = new ReentrantLock();
		final Condition condition = lock.newCondition();

		executor.execute(() ->
		{
			System.out.println("Thread sleeping");
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.println("Signaliing");
			lock.lock();
			try
			{
				condition.signalAll();
			}
			finally
			{
				lock.unlock();
			}
		});

		System.out.println("Main thread awaiting condition signal");
		lock.lock();
		try
		{
			condition.await();
		}
		finally
		{
			lock.unlock();
		}
		System.out.println("Main thread contnuing");

	}
}
