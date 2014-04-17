package richo.testproject.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Richo on 2014-04-17.
 */
public class JMSMessageConsumer implements Runnable, ExceptionListener
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	//constructors

	//methods


	@Override
	public void run() {
     try {

         // Create a ConnectionFactory
         ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

         // Create a Connection
         Connection connection = connectionFactory.createConnection();
         connection.start();

         connection.setExceptionListener(this);

         // Create a Session
         Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

         // Create the destination (Topic or Queue)
         Destination destination = session.createQueue("TEST.FOO");

         // Create a MessageConsumer from the Session to the Topic or Queue
         MessageConsumer consumer = session.createConsumer(destination);

         // Wait for a message
		 //todo loop or something
         Message message = consumer.receive(1000);

         if (message instanceof TextMessage) {
             TextMessage textMessage = (TextMessage) message;
             String text = textMessage.getText();
             System.out.println("Received: " + text);
         } else {
             System.out.println("Received: " + message);
         }

         consumer.close();
         session.close();
         connection.close();
     } catch (Exception e) {
         System.out.println("Caught: " + e);
         e.printStackTrace();
     }
 }

 public synchronized void onException(JMSException ex) {
     System.out.println("JMS Exception occured.  Shutting down client.");
 }
}
