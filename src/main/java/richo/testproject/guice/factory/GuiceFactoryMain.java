package richo.testproject.guice.factory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Richo on 2014-07-10.
 */
public class GuiceFactoryMain
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args)
	{
		new GuiceFactoryMain().stuff();
	}

	private void stuff()
	{
		Injector injector = Guice.createInjector(new InjectModule());
	}
}
