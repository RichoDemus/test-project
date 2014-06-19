package richo.testproject.web.jetty.jettysuntest.handlers;


import richo.testproject.web.jetty.jettysuntest.IHttpHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tjerngrr on 2014-06-18.
 */
public class NewServletHolder implements IHttpHandler
{
	private static final String PATH = "new";
	private static final String DESCRIPTION = "New servletholder";

	@Override
	public String getContextPath()
	{
		return PATH;
	}

	@Override
	public String getDescription()
	{
		return DESCRIPTION;
	}

	@Override
	public HttpServlet getServlet()
	{
		return new NewServlet();
	}

	private class NewServlet extends HttpServlet
	{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
		{
			super.doGet(req, resp);
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
		{
			super.doPost(req, resp);
		}
	}
}
