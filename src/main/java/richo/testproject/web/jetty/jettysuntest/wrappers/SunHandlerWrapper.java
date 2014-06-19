package richo.testproject.web.jetty.jettysuntest.wrappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Wrapper class for the old Sun Http Handlers <br/>
 * using this is deprecated, instead create a regular {@link javax.servlet.http.HttpServlet}
 */
@Deprecated
public class SunHandlerWrapper extends HttpServlet
{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final LegacyHttpHandler handler;

	/**
	 * Wraps the httphandler in a class that fulfills the servlet api <br/>
	 * using this is deprecated, instead create a regular {@link javax.servlet.http.HttpServlet}
	 * @param handler the handler to wrap
	 */
	@Deprecated
	public SunHandlerWrapper(LegacyHttpHandler handler)
	{
		this.handler = handler;
		logger.warn("Not yet implemented");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		logger.info("doGet");
		handler.handle(new LegacyHttpExchange(req, resp));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doPost(req, resp);
	}
}
