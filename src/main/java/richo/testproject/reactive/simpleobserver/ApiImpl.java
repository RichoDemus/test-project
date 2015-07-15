package richo.testproject.reactive.simpleobserver;

import rx.Observable;

import java.util.concurrent.atomic.AtomicLong;

public class ApiImpl implements Api
{
    private final AtomicLong counter = new AtomicLong();

    @Override
    public Observable<String> getSentence(String... words)
    {
        return Observable.from(words);
    }

    @Override
    public Observable<Long> getSequence()
    {
        return Observable.<Long>create(s ->
        {
            while (true)
            {
                s.onNext(counter.getAndIncrement());

                if (s.isUnsubscribed())
                {
                    s.onCompleted();
                    break;
                }
            }
        });
    }
}
