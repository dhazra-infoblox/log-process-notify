package notify;

import logging.LogType;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Message {
    private int id;
    private @NonNull LogType sender;
    private @NonNull String text;
    private @NonNull Long timeStamp;

    @Override
    public String toString() {
        return "Message{" +
                "from=" + sender +
                ", text='" + text + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}