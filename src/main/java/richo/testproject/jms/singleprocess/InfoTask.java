package richo.testproject.jms.singleprocess;

import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import java.util.Enumeration;

/**
 * Created by tjerngrr on 2014-04-17.
 */
public class InfoTask extends AbstractJMSInteracter implements Runnable
{
	@Override
	public void run()
	{
		try
		{
			connect();
			QueueBrowser browser = session.createBrowser(session.createQueue(QUEUE_NAME));

			Enumeration enumeration = browser.getEnumeration();
			int numberOfMessages = 0;
			while (enumeration.hasMoreElements())
			{
				enumeration.nextElement();
				numberOfMessages++;
				//Object o = enumeration.nextElement();
				//TextMessage msg = (TextMessage) o;
				//System.out.println(msg.getText());
			}
			System.out.println("Number of messages in queue: " + numberOfMessages);
			disconnect();
		}
		catch (JMSException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
	}
}
