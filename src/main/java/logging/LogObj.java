package logging;

import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

import java.util.PriorityQueue;
import java.util.Queue;

public class LogObj {
    private LogType logType;
    private @Setter Config config;
    private @Getter int frequency;
    private Queue<Long> timeStamps;

    public LogObj(LogType lt, Config c) {
        this.logType = lt;
        this.config = c;
        this.frequency = 0;
        this.timeStamps = new PriorityQueue<>(
                (n1, n2) -> (int) (n1 - n2)); // init heap 'the old timestamp first'
    }

    public void push(Long timeStamp) {
        this.timeStamps.add(timeStamp);
        if(timeStamps.size() > config.getFrequency()) {
            timeStamps.poll();
        } else {
            this.frequency++;
        }
    }

    public Long peekOldest() {
        return this.timeStamps.peek();
    }

    public void flush() {
        this.frequency = 0;
        this.timeStamps.clear();
    }
}