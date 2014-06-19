package richo.testproject.web.jetty.jettysuntest.wrappers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpPrincipal;
import org.apache.commons.lang3.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * Created by Richo on 2014-06-19.
 */
@Deprecated
public class LegacyHttpExchange
{
	private final HttpServletRequest req;
	private final HttpServletResponse resp;

	public LegacyHttpExchange(HttpServletRequest req, HttpServletResponse resp)
	{

		this.req = req;
		this.resp = resp;
	}

	public Headers getRequestHeaders()
	{
		Headers result = new Headers();

		for (String header : Collections.list(req.getHeaderNames()))
		{
			List<String> values = Collections.list(req.getHeaders(header));
			result.put(header, values);
		}

		return result;
	}

	public Headers getResponseHeaders()
	{
		throw new NotImplementedException("NYI");
	}

	public URI getRequestURI()
	{
		return URI.create(req.getRequestURI());
	}

	public String getRequestMethod()
	{
		return req.getMethod();
	}

	public HttpContext getHttpContext()
	{
		throw new NotImplementedException("NYI");
	}

	public void close()
	{
		throw new NotImplementedException("NYI");
	}

	public InputStream getRequestBody() throws IOException
	{
		return req.getInputStream();
	}

	public OutputStream getResponseBody() throws IOException
	{
		return resp.getOutputStream();
	}

	public void sendResponseHeaders(int status, long contentLength) throws java.io.IOException
	{
		resp.setStatus(status);
		resp.setContentLength((int) contentLength);
	}

	public InetSocketAddress getRemoteAddress()
	{
		throw new NotImplementedException("NYI");
	}

	public int getResponseCode()
	{
		throw new NotImplementedException("NYI");
	}

	public InetSocketAddress getLocalAddress()
	{
		throw new NotImplementedException("NYI");
	}

	public String getProtocol()
	{
		throw new NotImplementedException("NYI");
	}

	public Object getAttribute(String s)
	{
		throw new NotImplementedException("NYI");
	}

	public void setAttribute(String s, Object o)
	{
		throw new NotImplementedException("NYI");
	}

	public void setStreams(InputStream inputStream, OutputStream outputStream)
	{
		throw new NotImplementedException("NYI");
	}

	public HttpPrincipal getPrincipal()
	{
		throw new NotImplementedException("NYI");
	}
}
