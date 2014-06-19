package richo.testproject.web.jetty.jettysuntest.handlers;


import org.apache.commons.io.IOUtils;
import richo.testproject.web.jetty.jettysuntest.IHttpHandler;
import richo.testproject.web.jetty.jettysuntest.QueryString;
import richo.testproject.web.jetty.jettysuntest.wrappers.LegacyHttpExchange;
import richo.testproject.web.jetty.jettysuntest.wrappers.LegacyHttpHandler;
import richo.testproject.web.jetty.jettysuntest.wrappers.SunHandlerWrapper;

import java.io.IOException;
import java.io.OutputStream;

public class OldHandler implements IHttpHandler
{

	private static final String CONTEXT_PATH = "raw";
	private static final String DESCRIPTION = "Raw Http Requests";

	@Override
	public String getContextPath()
	{
		return CONTEXT_PATH;
	}

	@Override
	public String getDescription()
	{
		return DESCRIPTION;
	}

	@Override
	public javax.servlet.http.HttpServlet getServlet()
	{
		return new SunHandlerWrapper(new LegacyHttpHandler()
		{

			@Override
			public void handle(LegacyHttpExchange httpExchange) throws IOException
			{
				StringBuilder sb = new StringBuilder();

				sb.append("URI: [").append(httpExchange.getRequestURI()).append("]\n");

				sb.append("Request Method: [" + httpExchange.getRequestMethod() + "]\n");

				sb.append("Headers:\n");
				for (String key : httpExchange.getRequestHeaders().keySet())
				{
					sb.append("\tKey: [" + key + "]\n");
					for (String value : httpExchange.getRequestHeaders().get(key))
					{
						sb.append("\t\tValue: [" + value + "]\n");
					}
				}

				byte[] requestData = IOUtils.toByteArray(httpExchange.getRequestBody());

				sb.append("Body: [" + new String(requestData) + "]\n");

				System.out.println(sb.toString());

				byte[] response = sb.toString().getBytes();

				httpExchange.sendResponseHeaders(200, response.length);

				OutputStream os = httpExchange.getResponseBody();
				os.write(response);
				os.close();

				QueryString parameters = new QueryString(httpExchange.getRequestURI().getQuery());

				if (requestData != null && requestData.length > 0)
				{
					parameters.putAll(new QueryString(new String(requestData)));
				}

				if (!parameters.containsKey("ST.MSISDN"))
				{
					System.out.println("Did not find parameter ST.MSISDN in request, will not save in device store");
					return;
				}

			}

		});
	}

}
