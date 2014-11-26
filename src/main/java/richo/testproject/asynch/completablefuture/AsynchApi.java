package richo.testproject.asynch.completablefuture;

import java.util.concurrent.CompletableFuture;

public interface AsynchApi
{
	CompletableFuture<String> bounceString(String msg);
}
