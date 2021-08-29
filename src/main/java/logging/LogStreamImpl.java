package logging;

import lombok.AllArgsConstructor;
import org.graalvm.collections.Pair;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class LogStreamImpl implements LogStream{
    private List<Pair<LogType, Long>> logStream;
    private Map<LogType, LogObj> logCache;

    @Override
    public void process() {
        for (Pair<LogType, Long> log : logStream) {
            System.out.println("Processing .. " + log.getLeft() + " log at timestamp " + log.getRight());
            this.logCache.get(log.getLeft()).push(log.getRight());
        }
    }
}
