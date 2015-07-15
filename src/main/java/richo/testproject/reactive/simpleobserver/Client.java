package richo.testproject.reactive.simpleobserver;

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
}
