package richo.testproject.reactive.simpleobserver;

import rx.Observable;

public interface Api
{
    Observable<String> getSentence(String... words);
    Observable<Long> getSequence();
}
