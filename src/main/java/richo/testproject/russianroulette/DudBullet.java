package richo.testproject.russianroulette;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Richo on 2014-12-06.
 */
public class DudBullet implements Bullet
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean fire()
	{
		return false;
	}
}
