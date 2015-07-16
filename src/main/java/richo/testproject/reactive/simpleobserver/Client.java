package richo.testproject.reactive.simpleobserver;

import rx.Observable;
import rx.Subscriber;

public class Client
{
    public void foo()
    {
        final Api api = new ApiImpl();

        api.getSentence("Hello", "world", "dude")
                .doOnCompleted(() -> System.out.println("onComplete called for the sentence"))
                .doOnError((e) -> System.out.println("Error: " + e.getMessage()))
                .map(String::toUpperCase)
                .filter(s -> !s.startsWith("W"))
                .subscribe(s -> System.out.println("Got message : " + s));

        listToEntityStuff(api);


        api.getSequence()
                .skip(1)
                .takeUntil(l -> l >= 90)
                .filter(l -> l % 10 == 0)
                .subscribe(new Subscriber<Long>()
                {
                    @Override
                    public void onCompleted()
                    {
                        System.out.println("onComplete called for the sequence");
                    }

                    @Override
                    public void onError(Throwable throwable)
                    {

                    }

                    @Override
                    public void onNext(Long aLong)
                    {
                        System.out.println("Got sequence: " + aLong);
                        if (aLong >= 100)
                        {
                            unsubscribe();
                        }
                    }
                });
    }

    /**
     * Some experiments on how to convert a stream of lists (which you get when you buffer) back to the entities that were buffered
     *
     * @param api yadda yadda
     */
    private void listToEntityStuff(Api api)
    {
        api.getSequence()
                .takeUntil(l -> l >= 90)
                .filter(l -> l % 10 == 0)
                .buffer(10)
                .flatMapIterable(longs -> longs)
                .flatMap(Observable::just)
                .subscribe(l -> System.out.println("Buffered Sequence using two flatmaps: " + l));

        api.getSequence()
                .takeUntil(l -> l >= 90)
                .filter(l -> l % 10 == 0)
                .buffer(10)
                .compose(iterableObservable -> iterableObservable.flatMap(Observable::from))
                .subscribe(l -> System.out.println("Buffered Sequence with compose containing flatmap: " + l));

        api.getSequence()
                .takeUntil(l -> l >= 90)
                .filter(l -> l % 10 == 0)
                .buffer(10)
                .compose(iterableObservable -> iterableObservable.flatMap(Observable::from))
                .subscribe(l -> System.out.println("Buffered Sequence with flatmap and compose: " + l));

        api.getSequence()
                .takeUntil(l -> l >= 90)
                .filter(l -> l % 10 == 0)
                .buffer(10)
                .flatMap(Observable::from)
                .subscribe(l -> System.out.println("Buffered Sequence with just a flatmap: " + l));
    }
}
