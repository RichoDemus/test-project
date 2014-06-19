package richo.testproject.other;

/**
 * Created with IntelliJ IDEA.
 * User: tjerngrr
 * Date: 2013-11-28
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */
public class Progress
{
	private static final int NUMBER_OF_MESSAGES_TO_SEND = 1000;

	public static void main(String[] args)
	{
		for (int i = 0; i < NUMBER_OF_MESSAGES_TO_SEND; i++)
		{
			float percentage = ((float) i / (float) NUMBER_OF_MESSAGES_TO_SEND) * 100;
			//System.out.println("perc:" + percentage);
			if (percentage % 10 == 0)
			{
				System.out.println("ASDASDASDASDASDASD: " + i);
			}
		}
	}
}
