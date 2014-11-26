package richo.testproject.asynch.completablefuture;

import java.util.concurrent.ExecutionException;

/**
 * Created by tjerngrr on 2014-11-26.
 */
public class CompletableFutureMain
{
	public static void main(String[] args) throws ExecutionException, InterruptedException
	{
		final AsynchApi api = new ApiImpl();

		final String resp = api.bounceString("Hello World").get();
		System.out.println("Got string: " + resp);


		api.bounceString("Hello World again").thenAccept((msg) -> System.out.println("Got SecondString: " + msg));
	}
}
