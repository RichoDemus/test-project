package richo.testproject.guice.factory;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Created by Richo on 2014-07-10.
 */
public class ApiImpl implements Api
{
	final private String string;

	@Inject
	public ApiImpl(@Assisted String string)
	{
		this.string = string;
	}

	@Override
	public String getInjectedString()
	{
		return string;
	}
}
