import logging.*;
import notify.Topic;
import notify.User;

import java.util.*;

public class Application {
    public static void main(String[] args) throws InterruptedException {

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
