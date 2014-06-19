package richo.testproject.web.jetty.jettysuntest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;


public class QueryString extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;

	/**
	 * Parses a query string and makes map, suggested input is
	 * httpExchange.getRequestURI().getQuery()
	 * @param queryString in the format "externalId=1234&msisdn=12345&status=hejsan"
	 */
	public QueryString(String queryString) {
		if (queryString == null)
			return;
		for (String keyValue : queryString.split("&")) {
			String[] s = keyValue.split("=");
			if (s.length == 0)
				continue;
			String key = s[0];
			String value = s.length > 1 ? s[1] : "";
			put(key, value);
		}
	}

	@Override
	public String toString() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		MapUtils.debugPrint(ps, "QueryString content", this);
		String content = baos.toString();
		IOUtils.closeQuietly(ps);
		IOUtils.closeQuietly(baos);
		return content;
	}
}