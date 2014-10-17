package richo.testproject.web.jetty.staticcontent;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Richo on 2014-06-22.
 */
public class MyHandler extends AbstractHandler
{
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		resp.getOutputStream().write("<html><body><h1>Handler says hello world</h1></body></html>".getBytes());
		resp.getOutputStream().close();
	}
}
