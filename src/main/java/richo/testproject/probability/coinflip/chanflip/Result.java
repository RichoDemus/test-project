package richo.testproject.probability.coinflip.chanflip;

/**
 * Created by Richo on 2014-04-27.
 */
enum Result
{
	HEAD, TAILS;

	static Result get(int value)
	{
		if(value == 0)
		{
			return HEAD;
		}
		return TAILS;
	}
}
