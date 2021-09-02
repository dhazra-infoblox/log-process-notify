package logging;

import notify.Topic;
import org.javatuples.Triplet;

import java.util.*;

public class LogBrowserImpl implements LogBrowser {
    @Override
    public LogCache findLogsOf(LogType logType) {
        return this.properties.stream()
                .filter(x -> Objects.equals(x.getValue0().getLogType(), logType))
                .map(Triplet::getValue0)
                .findAny().get();
    }

    @Override
    public Config findConfigOf(LogType logType) {
        return this.properties.stream()
                .filter(x -> Objects.equals(x.getValue0().getLogType(), logType))
                .map(Triplet::getValue1)
                .findAny().get();
    }

    @Override
    public Topic findTopicOf(LogType logType) {
        return this.properties.stream()
                .filter(x -> Objects.equals(x.getValue0().getLogType(), logType))
                .map(Triplet::getValue2)
                .findAny().get();
    }

    private List<Triplet<LogCache, Config, Topic>> properties = new ArrayList<>();

    public void addProperty(LogType logType, Config config, Topic topic) {
        LogCache cache = new LogCache(logType);
        this.properties.add(new Triplet<>(cache, config, topic));
    }
}
