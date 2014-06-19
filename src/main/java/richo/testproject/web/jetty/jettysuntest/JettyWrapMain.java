package richo.testproject.web.jetty.jettysuntest;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richo.testproject.web.jetty.jettysuntest.handlers.NewServletHolder;
import richo.testproject.web.jetty.jettysuntest.handlers.OldHandler;
import richo.testproject.web.jetty.jettysuntest.handlers.PureServlet;
import richo.testproject.web.jetty.jettysuntest.handlers.PureServletWithTwoPaths;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by tjerngrr on 2014-06-18.
 */
public class JettyWrapMain
{
	private List<Servlet> servlets;
	private List<IHttpHandler> legacyHandlers = Collections.<IHttpHandler>emptyList();
	private String html;
	private Server server;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ServletUtil servletUtil;

	public static void main(String[] args) throws Exception
	{
		JettyWrapMain jettyWrapMain = new JettyWrapMain(Arrays.asList(new PureServlet(), new PureServletWithTwoPaths()),Arrays.asList(new NewServletHolder(), new OldHandler()));
		jettyWrapMain.start();
		jettyWrapMain.join();
	}

	public JettyWrapMain(List<Servlet> servlets, List<IHttpHandler> legacyHandlers)
	{
		this.servlets = servlets;
		this.legacyHandlers = legacyHandlers;
		servletUtil = new ServletUtil();
		server = new Server(80);
		updateHandlers();
	}

	private void start() throws Exception
	{
		server.start();
	}

	private void join() throws InterruptedException
	{
		server.join();
	}

	private void updateHandlers()
	{
		logger.debug("Updating handlers");

		//Create and set the root handler
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(new ServletHolder(new RootHandler()), "/*");

		//Create and add all the subpages
		final StringBuilder htmlList = new StringBuilder();

		htmlList.append("<ul>");
		for(Servlet servlet : servlets)
		{
			addServlet(context, htmlList, servlet);
		}
		for (IHttpHandler handler : legacyHandlers)
		{
			addLegacyHandler(context, htmlList, handler);
		}
		htmlList.append("</ul>");

		html = String.format(STATIC_HTML, htmlList);

	}

	private void addServlet(ServletContextHandler context, StringBuilder htmlList, Servlet servlet)
	{
		String[] paths;
		String description;
		try
		{
			paths = servletUtil.getPath(servlet.getClass());
			description = servletUtil.getDescription(servlet.getClass());
		}
		catch (InitializationException e)
		{
			logger.error("{} when attempting to read annotations for {}", e.getClass().getSimpleName(), servlet.getClass().getSimpleName(), e);
			return;
		}
		for(String path : paths)
		{
			context.addServlet(new ServletHolder(servlet), path);
			htmlList.append(String.format(LIST_ITEM, path, description));
			logger.debug("Added servlet {} with path {}", servlet.getClass().getSimpleName(), path);
		}
	}

	private void addLegacyHandler(ServletContextHandler context, StringBuilder htmlList, IHttpHandler handler)
	{
		String path = handler.getContextPath();
		String description = handler.getDescription();
		context.addServlet(new ServletHolder(handler.getServlet()), "/" + path);
		htmlList.append("<li><a href=\"").append(path).append("\">").append(description).append("</a></li>\n");
		logger.debug("Added handler {} with path {}", handler.getClass().getSimpleName(), path);
	}


	class RootHandler extends HttpServlet
	{
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			logger.debug("Get: {}",request.getPathInfo());
			//super.doGet(request, response);
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentLength(html.length());
			final OutputStream os = response.getOutputStream();
			os.write(html.getBytes());
			os.close();
		}
	}

	private static final String LIST_ITEM = "<li><a href=\"%s\">%s</a></li>\n";
	private static final String STATIC_HTML =
			"<html>\n" +
					"<head>\n" +
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n" +
					"<title>Device Simulator</title>\n" +
					"<style>\n" +
					"    body {\n" +
					"        background:pink;\n" +
					"        font-family: serif;\n" +
					"        font-size:18px;\n" +
					"    }\n" +
					"    div.msg {\n" +
					"      background:yellow;\n" +
					"    }\n" +
					"</style>\n" +
					"</head>\n" +
					"<body>\n" +
					"  <h1>Device Simulator</h1>\n" +
					"  <div>\n" +
					"    %s \n" +
					"  </div>\n" +
					"</body>\n" +
					"</html>\n";
}
