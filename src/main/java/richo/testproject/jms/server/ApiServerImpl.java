package richo.testproject.jms.server;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richo.testproject.jms.apistuff.api.Api;

import java.util.function.Consumer;

public class ApiServerImpl implements Api
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

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
