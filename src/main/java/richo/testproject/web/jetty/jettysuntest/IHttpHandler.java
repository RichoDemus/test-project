package richo.testproject.web.jetty.jettysuntest;

import javax.servlet.http.HttpServlet;

public interface IHttpHandler
{
	public String getContextPath();

	public String getDescription();

	public HttpServlet getServlet();
}
