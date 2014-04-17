package richo.testproject.bogosort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: tjerngrr
 * Date: 2013-03-21
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class BogoSorter
{
	private static Random r = new Random();

	public static void main(String[] args)
	{
		//int[] array = new int[]{5,7,4,7,9,7,5,4,3,2,34,6,7,8,6,5,3,4,5,76,46,45};
		int[] array = new int[]{5, 7, 4, 45, 100, 7, -10, 1337};

		while (!isSorted(array))
		{
			array = shuffle(array);
			System.out.println(Arrays.toString(array));
		}
		System.out.println("Done");
	}

	private static int[] shuffle(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			array = swapBits(array, r.nextInt(array.length), r.nextInt(array.length));
		}
		return array;
	}

	private static int[] swapBits(int[] array, int pos1, int pos2)
	{
		int holder = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = holder;
		return array;
	}

	public static boolean isSorted(int[] integers)
	{
		for (int i = 0; i < integers.length - 1; i++)
		{
			if (integers[i] > integers[i + 1])
			{
				return false;
			}
		}
		return true;
	}
}
