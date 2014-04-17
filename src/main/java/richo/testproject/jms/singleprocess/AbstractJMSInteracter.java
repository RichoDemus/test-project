package richo.testproject.jms.singleprocess;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by tjerngrr on 2014-04-17.
 */
class AbstractJMSInteracter
{
	protected static final String QUEUE_NAME = "TEST.FOO";
	protected Session session;
	protected Connection connection;

	protected void connect()  throws JMSException
	{
		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

		// Create a Connection
		connection = connectionFactory.createConnection();
		connection.start();

		// Create a Session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	protected void disconnect() throws JMSException
	{
		// Clean up
		session.close();
		connection.close();
	}
}
