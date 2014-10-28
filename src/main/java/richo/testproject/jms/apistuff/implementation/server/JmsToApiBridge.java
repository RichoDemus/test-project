package richo.testproject.jms.apistuff.implementation.server;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richo.testproject.jms.apistuff.api.Api;
import richo.testproject.jms.apistuff.implementation.client.ApiToJmsBridge;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JmsToApiBridge implements MessageListener, ExceptionListener
{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Api apiImplementation;

	private final Session session;
	private final Connection connection;
	private final MessageConsumer consumer;
	private final MessageProducer replyProducer;

	public JmsToApiBridge(@NotNull final Api apiImplementation, @NotNull final String brokerAddress) throws JMSException
	{
		this.apiImplementation = apiImplementation;

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerAddress);

		// Create a Connection
		connection = connectionFactory.createConnection();
		connection.start();

		// Create a Session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		connection.setExceptionListener(this);


		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue(ApiToJmsBridge.SYNC_BOUNCE_STRING);

		// Create a MessageConsumer from the Session to the Topic or Queue
		consumer = session.createConsumer(destination);
		consumer.setMessageListener(this);

		replyProducer = session.createProducer(null);
		replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}

	Message tmpMessage;

	@Override
	public void onMessage(Message message)
	{
		logger.debug("Got message: {}", message);
		if(message instanceof TextMessage)
		{
			final TextMessage text = (TextMessage) message;
			try
			{
				logger.debug("contents are: {}", text.getText());
				tmpMessage = message;
				apiImplementation.asynchBounceString(text.getText(), this::replyToAsyncStringBounce);
			} catch (JMSException e)
			{
				logger.error("Got exception: ", e);
			}
		}
	}

	private void replyToAsyncStringBounce(@NotNull final String msg)
	{
		logger.debug("about to send \"{}\" back", msg);

		TextMessage response = null;
		try
		{
			response = session.createTextMessage(msg);
			response.setJMSCorrelationID(tmpMessage.getJMSCorrelationID());

			replyProducer.send(tmpMessage.getJMSReplyTo(), response);
		} catch (JMSException e)
		{
			logger.error("Unable to send response back to client, ",e);
		}
	}

	@Override
	public void onException(JMSException e)
	{
		logger.error("OnException: ", e);
	}
}
