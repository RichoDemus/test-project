package richo.testproject.java8.stream;

import org.apache.commons.lang3.time.StopWatch;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by tjerngrr on 2014-10-09.
 */
public class StreamPerformanceMain
{
  private static final Logger logger = LoggerFactory.getLogger(StreamPerformanceMain.class);

  private final static int NUMBER_OF_OBJECTS = 50_000_000;
  //private final static int NUMBER_OF_OBJECTS = 200_000_000;
  //private final static int NUMBER_OF_OBJECTS = 500_000_000;
  //private final static int NUMBER_OF_OBJECTS = 40_000_000;
  private static final int DESIRED_ID = NUMBER_OF_OBJECTS/2;
  private static final int PRINT_FREQUENCY = 10;
  private static List<MyObj> objects;
  private static Map<Integer, MyObj> objectMap;
  private static int lastAmount = 0;
  private static long lastRun = System.currentTimeMillis();

  public static void main(String[] args)
  {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);


    System.out.println("Creating data structures");
    objects = new ArrayList<>(NUMBER_OF_OBJECTS);
    objectMap = new HashMap<>(NUMBER_OF_OBJECTS);
    System.out.println("Done creating data structures");

    executorService.scheduleWithFixedDelay(StreamPerformanceMain::printInfo, 0, PRINT_FREQUENCY, TimeUnit.SECONDS);

    StopWatch addWatch = new StopWatch();
    StopWatch streamWatch = new StopWatch();
    StopWatch parallelStreamWatch = new StopWatch();
    StopWatch forWatch = new StopWatch();
    StopWatch hashWatch = new StopWatch();

    System.out.println("Adding stuff");
    addWatch.start();
    objects = addStuff(objects);
    addWatch.stop();
    System.out.println("Done adding stuff");
    streamWatch.start();
    filterUsingStreams(objects);
    streamWatch.stop();
    parallelStreamWatch.start();
    filterUsingParallelStreams(objects);
    parallelStreamWatch.stop();
    forWatch.start();
    filterUsingLoop(objects);
    forWatch.stop();
    hashWatch.start();
    filterUsingHashmap(objectMap);
    hashWatch.stop();

    System.out.println("Took " + addWatch.getTime() + " to add objects");
    System.out.println("Took " + streamWatch.getTime() + " to filter using streams");
    System.out.println("Took " + parallelStreamWatch.getTime() + " to filter using parallel streams");
    System.out.println("Took " + forWatch.getTime() + " to filter using for-loop");
    System.out.println("Took " + hashWatch.getTime() + " to filter using HashMap");
    executorService.shutdownNow();
  }

  private static void filterUsingHashmap(Map<Integer, MyObj> objectMap)
  {
    System.out.println("Matched obj: " + objectMap.get(DESIRED_ID));
  }

  private static void printInfo()
  {
    try {
      final int amount = objects.size();
      final int numberOfCreatedObjects = amount - lastAmount;
      lastAmount = amount;

      final long timeSpent = System.currentTimeMillis() - lastRun;
      final long speed = (numberOfCreatedObjects / timeSpent) * 1000;
      final double percentage = ((double) amount / NUMBER_OF_OBJECTS) * 100;

      logger.info(amount + " created. " + numberOfCreatedObjects + " created since last print, " + speed + " created per second " +
          percentage + "% done");
      lastRun = System.currentTimeMillis();
    }
    catch(Exception e)
    {
      logger.error("",e);
    }
  }

  private static void filterUsingLoop(List<MyObj> objects) {
    for (MyObj object : objects) {
      if (object.id == DESIRED_ID) {
        System.out.println("Matched obj: " + object);
      }
    }
  }

  private static void filterUsingParallelStreams(List<MyObj> objects)
  {
    objects.parallelStream().filter(obj -> obj.id == DESIRED_ID).forEach(msg -> System.out.println("Matched obj: " + msg));
  }

  private static void filterUsingStreams(List<MyObj> objects)
  {
    objects.stream().filter(obj -> obj.id == DESIRED_ID).forEach(msg -> System.out.println("Matched obj: " + msg));
  }

  private static List<MyObj> addStuff(@NotNull final List<MyObj> objects)
  {
    List<MyObj> list = objects;
    for(int i = 0; i < NUMBER_OF_OBJECTS; i++)
    {
      final MyObj myObj = new MyObj(i);
      list.add(myObj);
      objectMap.put(myObj.id, myObj);
      Thread.yield();
    }
    return list;
  }

  private static class MyObj implements Comparable<MyObj>
  {

    private final int id;

    public MyObj(int i) {
      this.id = i;
    }

    @Override
    public int compareTo(@NotNull MyObj myObj) {
      return Integer.valueOf(this.id).compareTo(myObj.id);
    }

    @Override
    public String toString() {
      return "MyObj-" + id;
    }
  }
}
