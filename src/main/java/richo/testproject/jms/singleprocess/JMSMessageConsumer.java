package richo.testproject.jms.singleprocess;

import javax.jms.*;

/**
 * Created by Richo on 2014-04-17.
 */
public class JMSMessageConsumer extends AbstractJMSInteracter implements Runnable, ExceptionListener
{
	static final String KEYWORD_SHUTDOWN = "KEYWORD.SHUTDOWN";
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	private MessageConsumer consumer;
	private boolean running;

	//constructors

	//methods


	@Override
	public void run() {
		running = true;

		try {
			connect();


			// Wait for a message
			while(running)
			{
				//System.out.println("Listening for a message");
				Counters.numberOfReceives.incrementAndGet();
				Message message = consumer.receive(1000);

				if(message == null)
				{
					//System.out.println("Did not receive a message, waiting for a new one");
					continue;
				}

				Counters.numberOfReceivedMessages.incrementAndGet();

				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					//System.out.println("Received: " + text);
					if(text.startsWith(JMSMessageConsumer.KEYWORD_SHUTDOWN))
					{
						System.out.println("Received shutdown, shutting down");
						running = false;
					}
				} else {
					//System.out.println("Received: " + message);
				}

			}

			disconnect();
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}

	protected void connect() throws JMSException
	{
		super.connect();


		connection.setExceptionListener(this);


		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue("TEST.FOO");

		// Create a MessageConsumer from the Session to the Topic or Queue
		consumer = session.createConsumer(destination);
	}

	@Override
	protected void disconnect() throws JMSException
	{
		super.disconnect();
		consumer.close();
	}

	public synchronized void onException(JMSException ex) {
		System.out.println("JMS Exception occured.  Shutting down client.");
	}
}
