package richo.testproject.jms.serverclient;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Created by Richo on 2014-04-17.
 */
public class Producer implements ExceptionListener
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	protected Session session;
	protected Connection connection;
	private MessageConsumer consumer;
	private boolean running;
	private MessageProducer producer;

	//constructors

	//methods

	public static void main(String[] args) throws JMSException, IOException
	{
		new Producer().produce();
	}

	private void produce() throws JMSException, IOException
	{
		// Create a ConnectionFactory

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Consumer.BROKER_ADDRESS);

		// Create a Connection
		connection = connectionFactory.createConnection();
		connection.start();

		// Create a Session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		connection.setExceptionListener(this);

		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue(Consumer.QUEUE_NAME);

		// Create a MessageProducer from the Session to the Topic or Queue
		producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		TextMessage message = session.createTextMessage("Hello world!");

		// Tell the producer to send the message
		producer.send(message, DeliveryMode.NON_PERSISTENT, 4, 10000);

		session.close();
		connection.close();
	}

	public synchronized void onException(JMSException ex)
	{
		System.out.println("JMS Exception occured.  Shutting down client.");
	}
}
