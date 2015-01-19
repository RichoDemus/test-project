package richo.testproject.logicoperators;

/**
 * Created by richard.tjerngren on 2015-01-19.
 */
public class LogicOperatorsMain
{
	public static void main(String[] args)
	{
		new LogicOperatorsMain().start();
	}

	private void start()
	{
		if(first() | second() | third())
		{
			System.out.println("(first() | second() | third()) was run\n");
		}

		if(first() || second() || third())
		{
			System.out.println("(first() || second() || third()) was run\n");
		}

		if (!(!first() & !second() & !third()))
		{
			System.out.println("!(!first() & !second() & !third()) was run\n");
		}

		if (!(!first() && !second() && !third()))
		{
			System.out.println("!(!first() && !second() && !third()) was run\n");
		}
	}

	private boolean first()
	{
		System.out.println("First method called");
		return true;
	}

	private boolean second()
	{
		System.out.println("Second method called");
		return true;
	}

	private boolean third()
	{
		System.out.println("Third method called");
		return true;
	}
}
