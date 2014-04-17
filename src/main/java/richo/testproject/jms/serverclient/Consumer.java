package richo.testproject.jms.serverclient;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

import javax.jms.*;

/**
 * Created by Richo on 2014-04-17.
 */
public class Consumer implements ExceptionListener
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	protected static final String QUEUE_NAME = "TEST.FOO";
	private static final String KEYWORD_SHUTDOWN = "KEYWORD.SHUTDOWN";
	protected static final String BROKER_ADDRESS = "tcp://localhost:61616";
	protected Session session;
	protected Connection connection;
	private MessageConsumer consumer;
	private boolean running;

	//constructors

	//methods

	public static void main(String[] args) throws Exception
	{
		BrokerService broker = new BrokerService();

		// configure the broker
		broker.addConnector(BROKER_ADDRESS);

		broker.start();

		new Consumer().startConsuming();
	}

	private void startConsuming() throws JMSException
	{
		// Create a ConnectionFactory

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_ADDRESS);

		// Create a Connection
		connection = connectionFactory.createConnection();
		connection.start();

		// Create a Session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		connection.setExceptionListener(this);


		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue(QUEUE_NAME);

		// Create a MessageConsumer from the Session to the Topic or Queue
		consumer = session.createConsumer(destination);
		running = true;
		// Wait for a message
		while (running)
		{
			System.out.println("Listening for a message");
			Message message = consumer.receive(5000);

			if (message == null)
			{
				System.out.println("Did not receive a message, waiting for a new one");
				continue;
			}


			if (message instanceof TextMessage)
			{
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("Received: " + text);
				if (text.startsWith(Consumer.KEYWORD_SHUTDOWN))
				{
					System.out.println("Received shutdown, shutting down");
					running = false;
				}
			}
			else
			{
				System.out.println("Received: " + message);
			}

		}

		// Clean up
		session.close();
		connection.close();
	}

	public synchronized void onException(JMSException ex)
	{
		System.out.println("JMS Exception occured.  Shutting down client.");
	}
}
