package richo.testproject.jms.apistuff.mains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richo.testproject.jms.apistuff.api.Api;
import richo.testproject.jms.apistuff.implementation.server.ApiServerImpl;

public class ClientMain
{
	private static final Logger logger = LoggerFactory.getLogger(ClientMain.class.getName());

	public static void main(String[] args)
	{
		Api api = new ApiServerImpl();

		api.asynchBounceString("foo", (resp) -> {
			logger.info("Asynch: " + resp);
		});

		logger.info("Synch: " + api.syncBounceString("bar"));

	}
}
