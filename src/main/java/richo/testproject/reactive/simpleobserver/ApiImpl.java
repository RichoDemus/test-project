package richo.testproject.reactive.simpleobserver;

import rx.Observable;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

public class ApiImpl implements Api
{
    @Override
    public Observable<String> getSentence(String... words)
    {
        return Observable.from(words);
    }

    @Override
    public Observable<Long> getSequence()
    {
        return Observable.from(new CounterIterable());
    }

    private static class CounterIterable implements Iterable<Long>
    {
        private final AtomicLong counter = new AtomicLong();

        @Override
        public Iterator<Long> iterator()
        {
            return new Iterator<Long>()
            {
                @Override
                public boolean hasNext()
                {
                    return true;
                }

                @Override
                public Long next()
                {
                    return counter.getAndIncrement();
                }
            };
        }
    }
}
