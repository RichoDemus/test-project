package richo.testproject.web.jetty.staticcontent;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

/**
 * Created by Richo on 2014-06-22.
 */
public class JettyStaticTestMain
{

	private Server server;

	public static void main(String[] args) throws Exception
	{
		JettyStaticTestMain main = new JettyStaticTestMain();
		main.stuff();
	}

	private void stuff() throws Exception
	{
		server = new Server(80);

		/*
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		context.setContextPath("/servlet");
		JettyUtils.addServletsToContext(context, new Servlet[]{new HelloWorldServlet()});
		*/

		ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		servletContext.setContextPath("/servlet");
		servletContext.addServlet(new ServletHolder(new HelloWorldServlet()), "/");

		ContextHandler context = new ContextHandler("/hello");
		context.setContextPath("/hello");
		context.setHandler(new MyHandler());

		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(true);
		//resourceHandler.setWelcomeFiles(new String[]{"index.html"});
		//resourceHandler.setResourceBase("target/classes");
		//resourceHandler.setResourceBase(".");
		//resourceHandler.setBaseResource(Resource.newClassPathResource("."));
		resourceHandler.setBaseResource(Resource.newSystemResource("."));

		HandlerList handlers = new HandlerList();
		//handlers.setHandlers(new Handler[]{servletContext, context, resourceHandler, new DefaultHandler()});
		//handlers.setHandlers(new Handler[]{resourceHandler, new DefaultHandler()});
		handlers.setHandlers(new Handler[]{resourceHandler});
		server.setHandler(handlers);

		server.start();
		server.join();
	}
}
