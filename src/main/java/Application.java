import logging.*;
import notify.Topic;
import notify.User;

import java.util.*;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        /*
        Map<LogType, LogObj> logCache = new HashMap<>();
        List<User> users = Arrays.asList(new User("DJ"), new User("BRAVO"));
        logCache.put(LogType.INFO, new LogObj(LogType.INFO, new Config(10, 100, 100), new Topic(users)));
        logCache.put(LogType.WARNING, new LogObj(LogType.WARNING, new Config(10, 150, 100), new Topic(users)));
        logCache.put(LogType.CRITICAL, new LogObj(LogType.CRITICAL, new Config(10, 120, 100), new Topic(users)));
        logCache.put(LogType.BLOCKER, new LogObj(LogType.BLOCKER, new Config(10, 110, 100), new Topic(users)));

        List<Pair<LogType, Long>> stream = new ArrayList<>();

        Long currTime = System.currentTimeMillis();
        // generate some random dummy data
        for (int i = 0; i < 5000; i++) {
            stream.add(Pair.create(LogType.getRandom(), currTime += 5));
        }

        LogStream logStream = new LogStreamImpl(stream, logCache);
        logStream.process();
        */

        List<User> subscribers = Arrays.asList(new User("DJ"), new User("BRAVO"));

        // low-level module
        LogBrowserImpl properties = new LogBrowserImpl();
        properties.addProperty(LogType.INFO, new Config(10, 100, 100), new Topic(subscribers));
        properties.addProperty(LogType.WARNING, new Config(10, 150, 100), new Topic(subscribers));
        properties.addProperty(LogType.CRITICAL, new Config(10, 120, 100), new Topic(subscribers));
        properties.addProperty(LogType.BLOCKER, new Config(10, 110, 100), new Topic(subscribers));

        List<LogData> stream = new ArrayList<>();
        Long currTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            stream.add(new LogData(LogType.getRandom(), currTime += 5));
        }

        new LogStreamProcessor(properties, stream).process();
    }
}
