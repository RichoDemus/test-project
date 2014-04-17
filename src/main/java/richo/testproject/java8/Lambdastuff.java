package richo.testproject.java8;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

/**
 * Created by Richo on 2014-03-22.
 */
public class Lambdastuff
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	//constructors

	//methods

	public static void main(String[] args)
	{
		Runnable runner = () -> System.out.println("Hello world");

		runner.run();

		int x = 1, y = 2;
		//System.out.println( (x, y) -> x+y) );

		Printing pr = Lambdastuff::doPrint;
		pr.doPrint("Hello!");

		List<String> list = Arrays.asList("Hello", "world", "from", "list");

		Collections.sort(list, (String str1, String str2) -> str1.compareTo(str2));
		//System.out.println(Arrays.toString(list.toArray()));

		list.forEach(System.out::print);

		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5 ,6 ,7);

		pr.doPrint("\n");

		pr.doPrint(String.valueOf(getFirstMathcing(integers, i -> i.equals(Integer.valueOf(3)))));

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(Lambdastuff::staticMethod);
		executor.execute(() -> System.out.println("hehe"));
		executor.execute(executor::shutdown);

		List<Integer> newList = new ArrayList<>();
		integers.parallelStream().forEach(i ->
		{
			if (i % 2 == 0)
			{
				newList.add(i);
			}
			else
			{
				newList.add(0, i);
			}
		});


		newList.forEach(Lambdastuff::printNumber);

		System.out.println("Remove all even numbers");

		newList.removeIf(integer -> integer % 2 == 0);

		newList.forEach(Lambdastuff::printNumber);

	}

	/**
	 * prints the number
	 * @param integer int to print
	 */
	private static void printNumber(Integer integer)
	{
		System.out.println("The number is: " + integer);
	}

	@FunctionalInterface
	private interface Printing
	{
		void doPrint(String str);
	}


	private static void doPrint(String str)
	{
		System.out.println(str);
	}

	private static Integer getFirstMathcing(Collection<Integer> list, Predicate<Integer> predicate)
	{
		for(Integer i : list)
		{
			if(predicate.test(i))
			{
				return i;
			}
		}
		return null;
	}

	private static void staticMethod()
	{
		System.out.println("Hoho!");
	}
}
