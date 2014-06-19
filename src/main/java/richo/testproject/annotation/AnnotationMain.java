package richo.testproject.annotation;

import org.apache.activemq.kaha.impl.StoreLockedExcpetion;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Richo on 2014-06-17.
 */
public class AnnotationMain
{

	public static void main(String[] args) throws Exception
	{
		Class<AnnotatedClass> firstClass = AnnotatedClass.class;
		Class<AnotherAnnotatedClass> secondClass = AnotherAnnotatedClass.class;

		System.out.println("AnnotatedClass path is: " + getServletPath(firstClass));
		System.out.println("\nAnotherAnnotatedClass path is: " + getServletPath(secondClass));

	}

	public static String getServletPath(Object clazz) throws Exception
	{
		if(! (clazz instanceof Class))
		{
			throw new Exception("Wrong type: " + clazz.getClass().getSimpleName());
		}

		Class castedClass = (Class)clazz;

		if(!castedClass.isAnnotationPresent(WebServlet.class))
		{
			System.out.println("No annotation");
			throw new Exception("No Servlet annotation");
		}

		WebServlet annotation = (WebServlet) castedClass.getAnnotation(WebServlet.class);

		String[] urlParams = annotation.urlPatterns();

		String[] values = annotation.value();

		print(urlParams, "urlParam");
		print(values, "values");

		return null;
	}

	private static void print(String[] urlParams, String name) throws Exception
	{
		if(urlParams == null)
		{
			System.out.println(name + " is null");
			//throw new Exception("UrlParams is null");
			return;
		}

		if(urlParams.length == 0)
		{
			System.out.println("no " + name);
			return;
			//throw new Exception("no urlParams");
		}

		for (String param : urlParams)
		{
			System.out.println(name+ ": " + param);
		}
	}
}
