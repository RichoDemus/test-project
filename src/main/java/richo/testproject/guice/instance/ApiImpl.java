package richo.testproject.guice.instance;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

/**
 * Created by Richo on 2014-07-10.
 */
public class ApiImpl implements Api
{
	final private String string;

	@Inject
	public ApiImpl(@Named("myString")String string)
	{
		this.string = string;
	}

	@Override
	public String getInjectedString()
	{
		return string;
	}
}
