package richo.testproject.reactive.simpleobserver;

import rx.Observable;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class ApiImpl implements Api
{
    private final AtomicLong counter = new AtomicLong();

    @Override
    public Observable<String> getSentence()
    {
/*        return Observable.<String>create(s ->
        {
            s.onNext("Hello");
            s.onNext("World");
            s.onCompleted();
        });*/
        return Observable.just("hello", "world", "dude");
    }

    @Override
    public Observable<Long> getSequence()
    {
        return Observable.<Long>create(s ->
        {
            IntStream.range(0, 5).forEach(i -> s.onNext(counter.getAndIncrement()));
        });
    }
}
