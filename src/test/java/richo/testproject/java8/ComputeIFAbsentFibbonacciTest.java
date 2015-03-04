package richo.testproject.java8;

import com.richo.util.lang.loop.ForEachIndex;
import com.richo.util.lang.loop.Index;
import org.junit.Assert;
import org.junit.Test;

public class ComputeIFAbsentFibbonacciTest
{
  private final Integer[] fibb = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};

  @Test
  public void testFunctionWay() throws Exception
  {
	final ComputeIFAbsentFibbonacci calc = new ComputeIFAbsentFibbonacci();
	for (Index<Integer> index : ForEachIndex.create(fibb))
	{
	  Assert.assertEquals("Wrong, the " + (index.i + 1) + " fibonacci number should be", fibb[index.i].intValue(), calc.get(index.i + 1));
	}
  }

  @Test
  public void testOtherWay() throws Exception
  {
	final ComputeIFAbsentFibbonacci calc = new ComputeIFAbsentFibbonacci();
	for (Index<Integer> index : ForEachIndex.create(fibb))
	{
	  Assert.assertEquals("Wrong, the " + (index.i + 1) + " fibonacci number should be", fibb[index.i].intValue(), calc.anotherGet(index.i + 1));
	}
  }
}