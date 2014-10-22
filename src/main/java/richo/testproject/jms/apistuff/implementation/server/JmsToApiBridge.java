package richo.testproject.jms.apistuff.implementation.server;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richo.testproject.jms.apistuff.api.Api;

public class JmsToApiBridge
{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Api apiImplementation;

	public JmsToApiBridge(@NotNull final Api apiImplementation)
	{
		this.apiImplementation = apiImplementation;
	}
}
