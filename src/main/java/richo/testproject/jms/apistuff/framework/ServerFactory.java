package richo.testproject.jms.apistuff.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerFactory
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static <T> void create(Class<T> api, Class<? extends T> implementation)
	{
		
	}
}
