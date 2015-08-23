package richo.testproject.russianroulette;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Richo on 2014-12-06.
 */
public class RussianRouletteMain
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args)
	{
		new RussianRouletteMain().stuff();
	}

	private void stuff()
	{
		final Revolver revolver = new Revolver(6);
		revolver.reload();
		revolver.spin();



	}
}
