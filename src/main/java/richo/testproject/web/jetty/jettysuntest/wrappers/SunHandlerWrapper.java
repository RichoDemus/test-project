package richo.testproject.web.jetty.jettysuntest.wrappers;

import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;

/**
 * Wrapper class for the old Sun Http Handlers <br/>
 * using this is deprecated, instead create a regular {@link javax.servlet.http.HttpServlet}
 */
@Deprecated
public class SunHandlerWrapper extends HttpServlet
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Wraps the httphandler in a class that fulfills the servlet api <br/>
	 * using this is deprecated, instead create a regular {@link javax.servlet.http.HttpServlet}
	 * @param httpHandler the handler to wrap
	 */
	@Deprecated
	public SunHandlerWrapper(HttpHandler httpHandler)
	{
		logger.warn("Not yet implemented");
	}
}
