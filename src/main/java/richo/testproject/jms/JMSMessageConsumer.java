package richo.testproject.jms;

/**
 * Created by Richo on 2014-04-17.
 */
public class JMSMessageConsumer implements Runnable
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
		System.out.println("Now listening for JMS Messages");
		try
		{

		}
		finally
		{
			System.out.println("No longer listening for JMS Messages");
		}
	}
}
