package richo.testproject.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class GenericClassType
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) throws Exception
	{
		System.out.println(new LittlerClass<String>().getType());
	}

	private static abstract class LittleClass<T extends Object>
	{
		abstract String getType() throws Exception;


		private Class<T> inferedClass;

		public Class<T> getGenericClass() throws ClassNotFoundException
		{
			if (inferedClass == null)
			{
				Type mySuperclass = getClass().getGenericSuperclass();
				Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
				String className = tType.toString().split(" ")[1];
				System.out.println(className);
				//inferedClass = Class.forName(className);
			}
			return inferedClass;
		}
	}

	private static class LittlerClass<T extends Object> extends LittleClass<T>
	{
		@Override
		String getType() throws Exception
		{
			return getGenericClass().getSimpleName();
		}
	}
}
