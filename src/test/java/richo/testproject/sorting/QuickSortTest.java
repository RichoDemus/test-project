package richo.testproject.sorting;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: tjerngrr
 * Date: 2013-08-01
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class QuickSortTest
{
	@Test
	public void testSortTenNumbers() throws Exception
	{
		int[] expected = new int[]{1, 2, 3, 3, 6, 8};
		int[] result = new QuickSort(new int[]{1, 3, 3, 2, 8, 6}).sort();

		Assert.assertNotNull(result);
		Assert.assertArrayEquals(expected, result);
	}
}
