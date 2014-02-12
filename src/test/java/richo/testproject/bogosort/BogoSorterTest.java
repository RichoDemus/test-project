package richo.testproject.bogosort;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: tjerngrr
 * Date: 2013-03-21
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public class BogoSorterTest
{
	@Test
	public void testIsSorted() throws Exception
	{
		Assert.assertTrue(BogoSorter.isSorted(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
	}

	@Test
	public void testisSorted2() throws Exception
	{
		Assert.assertFalse(BogoSorter.isSorted(new int[]{1, 2, 1, 4, 5, 6, 7, 8, 9, 10}));
	}

	@Test
	public void testisSorted3() throws Exception
	{
		Assert.assertFalse(BogoSorter.isSorted(new int[]{10, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
	}
}
