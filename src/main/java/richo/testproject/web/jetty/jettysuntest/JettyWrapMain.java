package richo.testproject.web.jetty.jettysuntest;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import richo.testproject.web.jetty.jettysuntest.handlers.NewServletHolder;
import richo.testproject.web.jetty.jettysuntest.handlers.OldHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by tjerngrr on 2014-06-18.
 */
public class JettyWrapMain
{
	private List<IHttpHandler> handlers = Collections.<IHttpHandler>emptyList();
	private String html;
	private Server server;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public JettyWrapMain(List<IHttpHandler> iHttpHandlers)
	{
		this.handlers = iHttpHandlers;
		server = new Server(80);
		updateHandlers();
	}

	public static void main(String[] args) throws Exception
	{
		JettyWrapMain jettyWrapMain = new JettyWrapMain(Arrays.asList(new NewServletHolder(), new OldHandler()));
		jettyWrapMain.start();
		jettyWrapMain.join();
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
		List<Handler> handlerList = new ArrayList<Handler>();

		//Create and set the root handler
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(new ServletHolder(new RootHandler()), "/*");

		/*
		ContextHandler rootContext = new ContextHandler("/");
		rootContext.setHandler(new RootHandler());
		handlerList.add(rootContext);
		*/

		//Create and add all the subpages
		final StringBuilder htmlList = new StringBuilder();

		htmlList.append("<ul>");
		for (IHttpHandler handler : handlers) {
			context.addServlet(new ServletHolder(handler.getServlet()), "/" + handler.getContextPath());
			htmlList.append("<li><a href=\"").append(handler.getContextPath()).append("\">").append(handler.getDescription()).append("</a></li>\n");
			logger.debug("Added handler {} with path {}", handler.getClass().getSimpleName(), handler.getContextPath());
		}
		htmlList.append("</ul>");

		html = String.format(STATIC_HTML, htmlList);

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


	public static final String STATIC_HTML =
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
