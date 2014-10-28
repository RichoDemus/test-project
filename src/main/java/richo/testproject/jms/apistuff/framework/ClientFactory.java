package richo.testproject.jms.apistuff.framework;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richo.testproject.jms.apistuff.implementation.client.ApiToJmsBridge;

import javax.jms.JMSException;

public class ClientFactory
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@NotNull
	public static <T> T create(Class<T> api, @NotNull final String brokerAddress) throws JMSException
	{
		final ApiToJmsBridge apiToJmsBridge = new ApiToJmsBridge(brokerAddress);

		return api.cast(apiToJmsBridge);

	}
}
