package richo.testproject.jms.apistuff.implementation.client;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richo.testproject.jms.apistuff.api.Api;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.function.Consumer;

public class ApiToJmsBridge implements Api, ExceptionListener
{
	public static final String SYNC_BOUNCE_STRING = "syncBounceString";

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final Session session;
	private final Connection connection;
	private final MessageProducer producerForAsynchBounceString;

	public ApiToJmsBridge(@NotNull final String brokerAddress) throws JMSException
	{
		final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerAddress);

		connection = connectionFactory.createConnection();
		connection.start();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		connection.setExceptionListener(this);

		final Destination destination = session.createQueue(SYNC_BOUNCE_STRING);

		producerForAsynchBounceString = session.createProducer(destination);
		producerForAsynchBounceString.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

	}

	@Override
	public void asynchBounceString(@NotNull String msg, Consumer<String> callback)
	{
		logger.debug("Asych Bounce, putting message on queue");
		TextMessage message;
		try
		{
			message = session.createTextMessage("Hello world!");
		}
		catch (JMSException e)
		{
			logger.error("Unable to create message: ", e);
			return;
		}

		try
		{
			producerForAsynchBounceString.send(message);
		}
		catch (JMSException e)
		{
			logger.error("Unable to put message on queue: ", e);
			return;
		}
		logger.debug("Message sent");
	}

	@Override
	public String syncBounceString(@NotNull String msg)
	{
		logger.info("{}.{} called", this.getClass().getCanonicalName(), Thread.currentThread().getStackTrace()[1].getMethodName());
		return null;
	}

	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		session.close();
		connection.close();
	}

	@Override
	public void onException(JMSException e)
	{
		logger.error("OnException: ", e);
	}
}
