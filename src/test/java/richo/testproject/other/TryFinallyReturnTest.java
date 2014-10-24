package richo.testproject.other;

import org.junit.Test;

public class TryFinallyReturnTest
{
	private static final int NOT_SET = 0;
	private static final int TRY = 1;
	private static final int FINALLY = 2;

	@Test
	public void testName() throws Exception
	{

		final int i = myMethod();
		switch (i)
		{
			case NOT_SET:
				System.out.println("returned NOT_SET");
				break;
			case TRY:
				System.out.println("returned TRY");
				break;
			case FINALLY:
				System.out.println("returned FINALLY");
				break;
			default:
				System.out.println("returned " + i);
		}

	}

	int myInt = NOT_SET;

	private int myMethod()
	{
		try
		{
			return changeInt(TRY);
		}
		finally
		{
			return changeInt(FINALLY);
		}
	}

	private int changeInt(int val)
	{
		switch (val)
		{
			case NOT_SET:
				System.out.println("changed value to NOT_SET");
				break;
			case TRY:
				System.out.println("changed value to TRY");
				break;
			case FINALLY:
				System.out.println("changed value to FINALLY");
				break;
			default:
				System.out.println("changed value to " + val);
		}
		myInt = val;
		return myInt;
	}
}
