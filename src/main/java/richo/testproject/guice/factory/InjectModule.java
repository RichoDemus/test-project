package richo.testproject.guice.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InjectModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		install(new FactoryModuleBuilder().implement(Api.class, ApiImpl.class).build(ApiFactory.class));
	}

	interface ApiFactory
	{
		void create(String str);
	}
}
