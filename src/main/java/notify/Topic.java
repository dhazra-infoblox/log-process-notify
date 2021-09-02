package notify;

import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Topic {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private List<User> subscribers;
    private long lastTimeNotified;

    public Topic(List<User> subscribers) {
        this.id = count.incrementAndGet();
        this.subscribers = subscribers;
        this.lastTimeNotified = 0;
    }

    public boolean isNotifiable(int waitTime, Long timeStamp) {
        return timeStamp - lastTimeNotified >= waitTime;
    }

    public void notifyUsers(Message message) {
        this.lastTimeNotified = message.getTimeStamp();
        System.out.println("Topic " + id + " will notify its Users at " + this.lastTimeNotified);
        for (User user : this.subscribers) {
            user.sendMail(message);
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
