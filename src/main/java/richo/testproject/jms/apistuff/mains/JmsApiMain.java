package richo.testproject.jms.apistuff.mains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richo.testproject.jms.apistuff.api.Api;
import richo.testproject.jms.apistuff.framework.ClientFactory;
import richo.testproject.jms.apistuff.framework.ServerFactory;

public class JmsApiMain
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args)
	{
		new JmsApiMain().stuff();
	}

	private void stuff()
	{
		final Api client = ClientFactory.create(Api.class);

		ServerFactory.create(Api.class, new BackendImpl());
	}

	private class BackendImpl
	{
	}
}
