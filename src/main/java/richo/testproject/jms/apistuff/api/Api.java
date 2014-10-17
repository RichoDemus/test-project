package richo.testproject.jms.apistuff.api;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface Api
{
	void asynchBounceString(@NotNull String msg, Consumer<String> callback);

	String syncBounceString(@NotNull String msg);
}
