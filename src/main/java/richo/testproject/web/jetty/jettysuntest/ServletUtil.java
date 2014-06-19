package richo.testproject.web.jetty.jettysuntest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

/**
 * Created by Richo on 2014-06-19.
 */
public class ServletUtil
{
	@NotNull
	public String getDescription(Class<? extends Servlet> servlet) throws InitializationException
	{
		WebServlet annotation = getAnnotation(servlet);
		String description = annotation.description();
		if (StringUtils.isBlank(description))
		{
			throw new InitializationException("WebServlet description is empty or missing for class " + servlet.getSimpleName());
		}
		return description;
	}

	@NotNull
	public String[] getPath(Class<? extends Servlet> servlet) throws InitializationException
	{
		WebServlet annotation = getAnnotation(servlet);

		String[] paths = (String[]) ArrayUtils.addAll(annotation.urlPatterns(), annotation.value());
		if (paths.length == 0)
		{
			throw new InitializationException("No WebServlet annotation urlPatterns and no value for class " + servlet.getSimpleName());
		}
		return paths;
	}

	private void verifyAnnotationPresent(Class<? extends Servlet> servlet) throws InitializationException
	{
		if (!servlet.isAnnotationPresent(WebServlet.class))
		{
			throw new InitializationException("Servlet " + servlet.getClass().getSimpleName() + " is not annotated with WebServlet");
		}
	}

	@NotNull
	private WebServlet getAnnotation(Class<? extends Servlet> servlet) throws InitializationException
	{
		verifyAnnotationPresent(servlet);
		return servlet.getAnnotation(WebServlet.class);
	}
}
