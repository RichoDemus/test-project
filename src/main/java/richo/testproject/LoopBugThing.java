package richo.testproject;

/**
 * Created by tjerngrr on 2014-03-17.
 */
public class LoopBugThing
{
	public static void main(String[] args)
	{
		int i = 0;
		while(i < 10)
		{
			//do stuff here
			i = i++;
		}
		System.out.println("Done!");
	}
}
