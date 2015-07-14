package richo.testproject.reactive.simpleobserver;

import rx.Observable;

public class ApiImpl implements Api
{
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
}
