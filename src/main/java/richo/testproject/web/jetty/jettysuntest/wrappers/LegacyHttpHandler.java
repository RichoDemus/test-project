package richo.testproject.web.jetty.jettysuntest.wrappers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

/**
 * Created by Richo on 2014-06-19.
 */
@Deprecated
public interface LegacyHttpHandler
{
	void handle(HttpExchange httpExchange) throws IOException;
}
