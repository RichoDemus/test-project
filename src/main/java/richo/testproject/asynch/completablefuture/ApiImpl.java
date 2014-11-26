package richo.testproject.asynch.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * Created by tjerngrr on 2014-11-26.
 */
public class ApiImpl implements AsynchApi
{
	@Override
	public CompletableFuture<String> bounceString(String msg)
	{
		CompletableFuture<String> future = new CompletableFuture<>();

		Thread t = new Thread(() -> future.complete(msg));
		t.start();

		return future;
	}
}
