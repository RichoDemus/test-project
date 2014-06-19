package richo.testproject.web.jetty.jettysuntest.wrappers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
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
 * Wraps a {@link javax.servlet.http.HttpServletRequest} and {@link javax.servlet.http.HttpServletResponse} into a {@link com.sun.net.httpserver.HttpExchange}
 */
@Deprecated
public class HttpExchangeWrapper extends HttpExchange
{
	private final HttpServletRequest req;
	private final HttpServletResponse resp;

	public HttpExchangeWrapper(HttpServletRequest req, HttpServletResponse resp)
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

	public InputStream getRequestBody()
	{
		try
		{
			return req.getInputStream();
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	public OutputStream getResponseBody()
	{
		try
		{
			return resp.getOutputStream();
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
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
