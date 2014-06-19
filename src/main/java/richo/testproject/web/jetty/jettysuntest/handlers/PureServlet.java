package richo.testproject.web.jetty.jettysuntest.handlers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/pure", description = "Implementation using pure servlets")
public class PureServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String html = "<html><body><h1>Pure Servlet</h1></body></html>";
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentLength(html.getBytes().length);
		resp.getOutputStream().write(html.getBytes());
		resp.getOutputStream().close();
	}
}
