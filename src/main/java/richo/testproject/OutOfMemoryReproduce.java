package richo.testproject;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tjerngrr
 * Date: 2013-12-04
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
public class OutOfMemoryReproduce
{
	public static List<OutOfMemoryReproduce> list = new LinkedList<>();
	private final byte[] bytes;

	public OutOfMemoryReproduce(byte[] bytes)
	{
		this.bytes = bytes;
	}

	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Hello world");
		Thread.sleep(10000);
		long timestamp = 0;
		while(true)
		{
			list.add(new OutOfMemoryReproduce(new byte[]{1,1,1,1,1}));
			if(System.currentTimeMillis() - timestamp > 10 * 1000)
			{
				timestamp = System.currentTimeMillis();
				print();
				Thread.sleep(10);
			}
		}
	}

	private static void print()
	{
		Runtime runtime = Runtime.getRuntime();

		NumberFormat format = NumberFormat.getInstance();

		StringBuilder sb = new StringBuilder();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();

		sb.append("free memory: ").append(format.format(freeMemory / 1024)).append("\n");
		sb.append("allocated memory: ").append(format.format(allocatedMemory / 1024)).append("\n");;
		sb.append("max memory: ").append(format.format(maxMemory / 1024)).append("\n");
		sb.append("total free memory: ").append(format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024)).append("\n\n");
		System.out.println(sb);
	}
}
