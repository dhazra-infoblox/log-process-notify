package notify;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogNotifyEvent implements Event {
    private Message message;

    @Override
    public void send(Topic topic) {
        topic.notifyUsers(message);
    }
}
