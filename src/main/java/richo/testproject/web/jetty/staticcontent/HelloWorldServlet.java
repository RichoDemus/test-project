package richo.testproject.web.jetty.staticcontent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Richo on 2014-06-22.
 */
@WebServlet("/servlet")
public class HelloWorldServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.getOutputStream().write("<html><body><h1>Servlet says hello world</h1></body></html>".getBytes());
		resp.getOutputStream().close();
	}
}
