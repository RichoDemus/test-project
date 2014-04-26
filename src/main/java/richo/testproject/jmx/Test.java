package richo.testproject.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * JMX Bean
 */
public class Test implements TestMBean
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables
	private int value;

	//constructors

	//methods

	@Override
	public void setInteger(int value)
	{
		System.out.println("SetInteger: " + value);
		this.value = value;
	}

	@Override
	public int getInteger()
	{
		System.out.println("Get Integer");
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		Arrays.asList(stackTrace).forEach(System.out::println);
		return value;
	}

	private int getIntegerSilent()
	{
		return value;
	}

	@Override
	public int doDoubleInteger()
	{
		System.out.println("Double Integer");
		value *= 2;
		return value;
	}

	public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException
	{
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

		ObjectName name = new ObjectName("com.richo.jmx:type=Test");

		Test test = new Test();
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(() -> System.out.println("Value is : " + test.getIntegerSilent()), 0, 10, TimeUnit.SECONDS);

		mBeanServer.registerMBean(test, name);
		while(test.getIntegerSilent() != -1)
		{
			Thread.sleep(1000);
		}
		executor.shutdownNow();
	}
}
