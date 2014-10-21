package richo.testproject.jms.apistuff.framework;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientFactory
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@NotNull
	public static <T> T create(Class<T> api)
	{
		return null;
	}
}
