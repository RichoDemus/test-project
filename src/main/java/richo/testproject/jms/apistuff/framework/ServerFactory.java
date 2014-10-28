package richo.testproject.jms.apistuff.framework;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richo.testproject.jms.apistuff.api.Api;
import richo.testproject.jms.apistuff.implementation.server.JmsToApiBridge;

import javax.jms.JMSException;

public class ServerFactory
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static <T> void create(final @NotNull Api implementation, @NotNull final String brokerAddress) throws JMSException
	{
		new JmsToApiBridge(implementation, brokerAddress);
	}
}
