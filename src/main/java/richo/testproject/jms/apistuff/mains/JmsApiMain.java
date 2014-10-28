package richo.testproject.jms.apistuff.mains;

import org.apache.activemq.broker.BrokerService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richo.testproject.jms.apistuff.api.Api;
import richo.testproject.jms.apistuff.framework.ClientFactory;
import richo.testproject.jms.apistuff.framework.ServerFactory;

import java.util.function.Consumer;

public class JmsApiMain
{
	private static final String BROKER_ADDRESS = "tcp://localhost:61616";
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) throws Exception
	{
		new JmsApiMain().stuff();
	}

	private void stuff() throws Exception
	{
		setupBroker();

		final Api client = ClientFactory.create(Api.class, BROKER_ADDRESS);

		ServerFactory.create(new BackendImpl(), BROKER_ADDRESS);

		logger.info("Stuff initialized");

		logger.info(client.syncBounceString("If this is readable, synchronous bounce works"));

		client.asynchBounceString("IF this is readable, async bounce works", System.out::println);

		Thread.sleep(1000);
	}



	private void setupBroker() throws Exception
	{
		BrokerService broker = new BrokerService();

		// configure the broker
		broker.addConnector(BROKER_ADDRESS);

		broker.start();
	}


	private class BackendImpl implements Api
	{
		@Override
		public void asynchBounceString(@NotNull String msg, Consumer<String> callback)
		{
			logger.info("asynchBounceString in backend");
			callback.accept(msg);
		}

		@Override
		public String syncBounceString(@NotNull String msg)
		{
			logger.info("syncBounceString in backend");
			return msg;
		}
	}
}
