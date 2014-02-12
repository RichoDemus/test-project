package richo.testproject;

/**
 * Created with IntelliJ IDEA.
 * User: tjerngrr
 * Date: 2013-03-27
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
public class PasswordStuff
{
	private static int record = 0;
	public static void main(String[] args) throws Exception
	{
		long start = System.currentTimeMillis();
		for(int i = 0; i < 100000000; i++)
		{
			//System.out.println();
			generatePassword();
		}
		System.out.println("Worst case was " + record + " generations, took " + (System.currentTimeMillis()-start) + "ms");
	}
	protected static String generatePassword() throws Exception
	{
		String password;
		int i = 0;
		//System.out.println("NEW");
		do {
			password = Long.toString(Math.round(Math.random() * 10000));
			//System.out.println(password);
			i++;
		} while (password.length() != 4);
		if(i > record)
		{
			record = i;
		}
		return password;
	}
}
