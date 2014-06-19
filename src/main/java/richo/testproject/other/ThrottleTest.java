package richo.testproject.other;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang.time.StopWatch;

import java.io.IOException;


public class ThrottleTest
{
	final static double rate = 200.0;
	final static RateLimiter rateLimiter = RateLimiter.create(rate);

	public static void main(String[] args) throws IOException, InterruptedException
	{
		final int TOTAL_REQUESTS = (int) (rate * 20);
		final int REQUESTS_PER_SECOND = 10;

		long requestsSent = 0;
		StopWatch watch = new StopWatch();
		watch.start();
		for (; requestsSent < TOTAL_REQUESTS; requestsSent++)
		{
			rateLimiter.acquire();
		}
		watch.stop();
		double requests = requestsSent;
		double timeInSeconds = watch.getTime() / 1000;
		System.out.println("Sent " + requestsSent + " in " + watch.toString() + " which is " + requests / timeInSeconds + " requests per second");
		System.out.println(timeInSeconds);
	}
}
