package richo.testproject.jms;

/**
 * Created by Richo on 2014-04-17.
 */
public class JMSMessageProducer implements Runnable
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	//constructors

	//methods


	@Override
	public void run()
	{
		System.out.println("" + this.getClass().getCanonicalName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + " called");
	}
}
