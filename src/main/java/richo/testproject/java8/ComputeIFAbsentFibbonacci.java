package richo.testproject.java8;

import java.util.HashMap;
import java.util.function.Function;


public class ComputeIFAbsentFibbonacci
{
  public static void main(String[] args)
  {
	final ComputeIFAbsentFibbonacci comp = new ComputeIFAbsentFibbonacci();

	System.out.println(comp.get(3) + " should be 2");
	System.out.println(comp.get(10) + " should be 55");
	System.out.println(comp.get(30) + " should be 268435456");
  }



  final HashMap<Integer, Integer> cache;

  public ComputeIFAbsentFibbonacci()
  {
	cache = new HashMap<>();
	cache.put(1, 1);
	cache.put(2, 1);
  }

  private final Function<Integer, Integer> fibonacci = new Function<Integer, Integer>()
  {
	@Override
	public Integer apply(Integer integer)
	{
	  return cache.computeIfAbsent(integer - 1, fibonacci) + cache.computeIfAbsent(integer - 1, fibonacci);
	}
  };

  private int get(int i)
  {
	return cache.computeIfAbsent(i, fibonacci);
  }
}
