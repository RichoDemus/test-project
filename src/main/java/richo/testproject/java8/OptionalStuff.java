package richo.testproject.java8;


import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import javax.validation.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Richo on 2014-04-03.
 */
public class OptionalStuff
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	//constructors

	public void stuff()
	{
		System.out.println("" + this.getClass().getCanonicalName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + " called");

	}


	//methods

	public static void main(String[] args)
	{
		Optional<String> mid = Optional.ofNullable(getString());

		System.out.println(mid.orElse("hello world"));
		System.out.println(mid.orElseGet(() -> "hehe"));

		System.out.println(mid.orElseThrow(() -> new RuntimeException("exception")));

	}


	private static String getString()
	{
		return null;
	}

	public interface Hejsan
	{
		void stuff();
	}
}
