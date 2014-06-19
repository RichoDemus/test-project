package richo.testproject.other;

/**
 * Created with IntelliJ IDEA.
 * User: tjerngrr
 * Date: 2013-12-06
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public class PergMenReproduce
{
	public static void main(String[] args) throws InterruptedException
	{
		Thread.sleep(20 * 1000);
		recurse(new String[]{"whatever"});
	}

	private static void recurse(String[] args)
	{
		recurse(new String[]{(args[0] + args[0]).intern()});
	}
}
