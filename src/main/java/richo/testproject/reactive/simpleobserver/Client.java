package richo.testproject.reactive.simpleobserver;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.List;

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
     * Some experiments on how to convert a stream of lists (which you get when you buffer) back to the entities that were buffered </br>
     * it seems like the solution that uses listObservable or ListToEntryTransform is preferred because compose is applied to the entire stream while flatMap will </br>
     * have to be run for each entity that passes through </br>
     * http://blog.danlew.net/2015/03/02/dont-break-the-chain/ </br>
     *
     * @param api yadda yadda
     */
    private void listToEntityStuff(Api api)
    {
        final Observable.Transformer<List<Long>, Long> listToEntries = listObservable -> listObservable.flatMap(Observable::from);

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
                .compose(listToEntries)
                .subscribe(l -> System.out.println("Buffered Sequence with compose on saved lambda: " + l));

        api.getSequence()
                .takeUntil(l -> l >= 90)
                .filter(l -> l % 10 == 0)
                .buffer(10)
                .compose(new ListToEntryTransform<>())
                .subscribe(l -> System.out.println("Buffered Sequence with compose on generic class: " + l));

        api.getSequence()
                .takeUntil(l -> l >= 90)
                .filter(l -> l % 10 == 0)
                .buffer(10)
                .flatMap(Observable::from)
                .subscribe(l -> System.out.println("Buffered Sequence with just a flatmap: " + l));
    }

    private static class ListToEntryTransform<T> implements Observable.Transformer<Iterable<T>, T>
    {
        @Override
        public Observable<T> call(Observable<Iterable<T>> iterableObservable)
        {
            return iterableObservable.flatMap(Observable::from);
        }
    }
}
