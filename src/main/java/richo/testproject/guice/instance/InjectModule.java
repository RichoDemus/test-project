package richo.testproject.guice.instance;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Richo on 2014-07-10.
 */
public class InjectModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(String.class).annotatedWith(Names.named("myString")).toInstance("Hello World");
		bind(Api.class).to(ApiImpl.class);
	}
}
