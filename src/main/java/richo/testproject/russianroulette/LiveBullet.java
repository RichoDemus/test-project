package richo.testproject.russianroulette;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Richo on 2014-12-06.
 */
public class LiveBullet implements Bullet
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private boolean hasBeenFired = false;

	@Override
	public boolean fire()
	{
		if(hasBeenFired)
		{
			throw new RuntimeException("This round has already been fired");
		}
		hasBeenFired = true;
		return true;
	}
}
