package richo.testproject.sorting;

/**
 * Created with IntelliJ IDEA.
 * User: tjerngrr
 * Date: 2013-08-01
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
public class QuickSort
{
	private final int[] array;

	public QuickSort(int[] array)
	{
		this.array = array;
	}

	public int[] sort()
	{
		for(int i = 0; i < array.length -1; i++)
		{
			for(int j = i+1; j < array.length; j++)
			{
				if(array[i] > array[j])
				{
					swap(i, j);
				}
			}
		}
		return array;
	}

	private void swap(int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
