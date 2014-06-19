package richo.testproject.web.jetty.jettysuntest.wrappers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpPrincipal;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Object;
import java.lang.String;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * Created by Richo on 2014-06-19.
 */
@Deprecated
public interface LegacyHttpExchange
{
	Headers getRequestHeaders();

	Headers getResponseHeaders();

	URI getRequestURI();

	String getRequestMethod();

	HttpContext getHttpContext();

	void close();

	InputStream getRequestBody();

	OutputStream getResponseBody();

	void sendResponseHeaders(int i, long l) throws java.io.IOException;

	InetSocketAddress getRemoteAddress();

	int getResponseCode();

	InetSocketAddress getLocalAddress();

	String getProtocol();

	Object getAttribute(String s);

	void setAttribute(String s, Object o);

	void setStreams(InputStream inputStream, OutputStream outputStream);

	HttpPrincipal getPrincipal();
}
