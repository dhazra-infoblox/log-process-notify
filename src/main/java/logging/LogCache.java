package logging;

import lombok.Getter;

import java.util.PriorityQueue;
import java.util.Queue;

@Getter
public class LogCache {
    private LogType logType;
    private Queue<LogData> logs;

    public LogCache(LogType logType) {
        this.logType = logType;
        this.logs = new PriorityQueue<>(
                (log1, log2) -> (int) (log1.getTimeStamp() - log2.getTimeStamp())
        );
    }

    public int size() {
        return this.logs.size();
    }

    public void push(LogData logData) {
        this.logs.add(logData);
    }

    public LogData peekOldest() {
        return this.logs.peek();
    }

    public void removeOldest() {
        this.logs.poll();
    }

    public void flush() {
        this.logs.clear();
    }
}
