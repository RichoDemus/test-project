package richo.testproject.web.jetty.jettysuntest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

public class ServletUtilTest
{
	private static final String[] PATHS = {"url1", "url2"};
	private ServletUtil util;

	@Before
	public void setUp() throws Exception
	{
		util = new ServletUtil();
	}

	@Test(expected = InitializationException.class)
	public void testGetDescriptionNoAnnotation() throws Exception, InitializationException
	{
		util.getDescription(NoAnnotation.class);
	}

	@Test(expected = InitializationException.class)
	public void testGetDescriptionBlankAnnotation() throws Exception, InitializationException
	{
		util.getDescription(BlankAnnotation.class);
	}

	@Test(expected = InitializationException.class)
	public void testGetDescriptionBlankDescription() throws Exception, InitializationException
	{
		util.getDescription(BlankDescription.class);
	}

	@Test(expected = InitializationException.class)
	public void testGetDescriptionNoDescription() throws Exception, InitializationException
	{
		util.getDescription(NoDescription.class);
	}

	@Test
	public void testGetAnnotation() throws Exception, InitializationException
	{
		Assert.assertEquals(OnlyDescription.DESCRIPTION, util.getDescription(OnlyDescription.class));
		Assert.assertEquals(OnePathAndDescription.DESCRIPTION, util.getDescription(OnePathAndDescription.class));
		Assert.assertEquals(TwoPathsAndDescription.DESCRIPTION, util.getDescription(TwoPathsAndDescription.class));
	}


	@Test(expected = InitializationException.class)
	public void testGetPathsNoAnnotation() throws Exception, InitializationException
	{
		util.getPath(NoAnnotation.class);
	}

	@Test(expected = InitializationException.class)
	public void testGetPathsBlankAnnotation() throws Exception, InitializationException
	{
		util.getPath(BlankAnnotation.class);
	}

	@Test
	public void testGetPaths() throws Exception, InitializationException
	{
		Assert.assertArrayEquals(new String[]{"url"}, util.getPath(NoDescription.class));
		Assert.assertArrayEquals(new String[]{"url"}, util.getPath(OnePathAndDescription.class));
		Assert.assertArrayEquals(new String[]{"url1", "url2"}, util.getPath(TwoPathsAndDescription.class));
	}

	private class NoAnnotation extends HttpServlet {}

	@WebServlet
	private class BlankAnnotation extends HttpServlet {}

	@WebServlet(NoDescription.PATHS)
	private static class NoDescription extends HttpServlet {
		public static final String PATHS = "url";
	}

	@WebServlet(description = "")
	private static class BlankDescription extends HttpServlet {}

	@WebServlet(description = OnlyDescription.DESCRIPTION)
	private static class OnlyDescription extends HttpServlet {
		public static final String DESCRIPTION = "desc";
	}

	@WebServlet(urlPatterns = OnePathAndDescription.PATHS, description = OnePathAndDescription.DESCRIPTION)
	private static class OnePathAndDescription extends HttpServlet {
		public static final String PATHS = "url";
		public static final String DESCRIPTION = "desc";
	}

	@WebServlet(urlPatterns = {"url1", "url2"}, description = TwoPathsAndDescription.DESCRIPTION)
	private static class TwoPathsAndDescription extends HttpServlet {

		public static final String DESCRIPTION = "desc";
	}
}