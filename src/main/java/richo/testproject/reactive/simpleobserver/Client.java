package richo.testproject.reactive.simpleobserver;

public class Client
{
    public void foo()
    {
        final Api api = new ApiImpl();

        api.getSentence()
                .doOnCompleted(() -> System.out.println("Completed"))
                .doOnError((e) -> System.out.println("Error: " + e.getMessage()))
                .map(String::toUpperCase)
                .filter(s -> !s.startsWith("W"))
                .subscribe(s -> System.out.println("Got message : " + s));

        api.getSequence().subscribe(s -> System.out.println("Got sequence: " + s));
    }
}
