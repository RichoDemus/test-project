package richo.testproject.jms;

import javax.jms.*;

/**
 * Created by Richo on 2014-04-17.
 */
public class JMSMessageProducer extends AbstractJMSInteracter implements Runnable
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	private MessageProducer producer;
	private final String msg;
	private final int numberOfMessagesToSend;

	//constructors

	public JMSMessageProducer(String msg, int numberOfMessagesToSend)
	{
		this.msg = msg;
		this.numberOfMessagesToSend = numberOfMessagesToSend;
	}

	public JMSMessageProducer(String keywordShutdown)
	{
		this(keywordShutdown, 1);
	}


	//methods


	@Override
	public void run() {
     try {
		 connect();

         //System.out.println("Sent message: ["+ message.getText() + "] : " + Thread.currentThread().getName());
	     for(int i = 0; i < numberOfMessagesToSend; i++)
	     {
		     // Create a messages
		     TextMessage message = session.createTextMessage(msg + ": " + i);

		     // Tell the producer to send the message
		     producer.send(message, DeliveryMode.NON_PERSISTENT, 4, 10000);
		     Counters.numberOfSentMessages.incrementAndGet();
	     }

		 disconnect();


	 }
     catch (Exception e) {
         System.out.println("Caught: " + e);
         e.printStackTrace();
     }
 }

	protected void connect() throws JMSException
	{
		super.connect();

		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue(QUEUE_NAME);

		// Create a MessageProducer from the Session to the Topic or Queue
		producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}
}
