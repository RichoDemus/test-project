package richo.testproject.web.jetty.staticcontent;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;

public class CleanedJettyStaticMain
{
	public static void main(String[] args) throws Exception
	{
		Server server = new Server(80);

		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(true);
		resourceHandler.setBaseResource(Resource.newClassPathResource("/"));
		server.setHandler(resourceHandler);

		server.start();
		server.join();
	}
}
