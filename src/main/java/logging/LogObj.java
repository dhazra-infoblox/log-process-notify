package logging;

import lombok.Getter;
import lombok.Setter;
import notify.Topic;

import java.util.Queue;

import java.util.PriorityQueue;

public class LogObj {
    private LogType logType;
    private @Setter Config config;
    private @Getter int frequency;
    private Queue<Long> timeStamps;

    private Topic topic;

    public LogObj(LogType lt, Config c, Topic topic) {
        this.logType = lt;
        this.config = c;
        this.topic = topic;
        this.frequency = 0;
        this.timeStamps = new PriorityQueue<>(
                (n1, n2) -> (int) (n1 - n2)); // init heap 'the old timestamp first'
    }

    public void push(Long timeStamp) {
        this.timeStamps.add(timeStamp);
        if(timeStamps.size() > config.getFrequency()) {
            if(timeStamp - config.getDuration() <= peekOldest()) {
                System.out.println(logType + " time: " + timeStamp + " head: " + peekOldest());
                sendEvent(timeStamp);
            }
            timeStamps.poll();
        }
        this.frequency++;
    }

    public Long peekOldest() {
        return this.timeStamps.peek();
    }

    public void flush() {
        this.frequency = 0;
        this.timeStamps.clear();
    }

    public void sendEvent(Long timeStamp) {
        if (this.topic.isNotifiable(config.getWaitTime(), timeStamp)) {
            this.topic.setLastTimeNotified(timeStamp);
            this.topic.notifyUsers();
            flush();
        } else {
            System.out.println("WaitTime not over");
        }
    }
}