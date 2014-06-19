package richo.testproject.web.jetty.jettysuntest.wrappers;

import java.io.IOException;

/**
 * Created by Richo on 2014-06-19.
 */
public interface LegacyHttpHandler
{
	void handle(LegacyHttpExchange httpExchange) throws IOException;
}
