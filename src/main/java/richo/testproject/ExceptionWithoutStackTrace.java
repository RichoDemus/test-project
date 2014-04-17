package richo.testproject;

/**
 * Created by tjerngrr on 2014-04-07.
 */
public class ExceptionWithoutStackTrace
{
	public static void main(String[] args)
	{
		ArrayIndexOutOfBoundsException e = new ArrayIndexOutOfBoundsException("null");
		e.initCause(null);

		throw e;
	}
}
