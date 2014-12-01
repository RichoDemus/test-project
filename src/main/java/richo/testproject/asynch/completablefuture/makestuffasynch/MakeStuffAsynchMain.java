package richo.testproject.asynch.completablefuture.makestuffasynch;

import java.util.concurrent.CompletableFuture;

public class MakeStuffAsynchMain
{
	public static void main(String[] args)
	{
		new MakeStuffAsynchMain().stuff();
	}

	private void stuff()
	{
		CompletableFuture.supplyAsync(() -> synchBounce("Hello World")).thenAccept((msg) -> System.out.println("Got String: " + msg));
	}


	private String synchBounce(String msg)
	{
		return msg;
	}
}
