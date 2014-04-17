package richo.testproject.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Richo on 2014-04-17.
 */
public class JMSMessageProducer implements Runnable
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	private MessageProducer producer;
	private Session session;
	private Connection connection;

	//constructors

	//methods


	@Override
	public void run() {
     try {
		 connect();


		 // Create a messages
         String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
         TextMessage message = session.createTextMessage(text);

         // Tell the producer to send the message
         System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
         producer.send(message);


		 disconnect();


	 }
     catch (Exception e) {
         System.out.println("Caught: " + e);
         e.printStackTrace();
     }
 }

	private void disconnect() throws JMSException
	{
		// Clean up
		session.close();
		connection.close();
	}

	private void connect() throws JMSException
	{
		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

		// Create a Connection
		connection = connectionFactory.createConnection();
		connection.start();

		// Create a Session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue("TEST.FOO");

		// Create a MessageProducer from the Session to the Topic or Queue
		producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}
}
