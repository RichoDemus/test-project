package richo.testproject.java8;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

public class MoreLambdaStuff {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        new MoreLambdaStuff().run();
    }

    private void run() {
        Arrays.asList("one", "two", "three", "four").stream().map(String::toUpperCase).forEach(System.out::println);
        Arrays.asList(Arrays.asList("one-one", "one-two"), Arrays.asList("two-one", "two-two"), Arrays.asList("three-one", "three-two")).stream().flatMap(Collection::stream).forEach(System.out::println);
        ImmutableMap.of("key1", "val1", "key2", "val2", "key3", "val3").entrySet().stream().flatMap(entry -> Arrays.asList(entry.getKey(), entry.getValue()).stream()).forEach(System.out::println);
        Arrays.asList("one", "two", "three", "four").stream().reduce((s, s2) -> s + s2).map(s -> "Result from reduce:" + s).ifPresent(logger::info);
    }
}
