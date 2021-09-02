package notify;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User {
    private final String name;
    private String id;

    public void sendMail(Message message) {
        System.out.println("Mail " + message.toString() + " sent to " + this.name);
    }
}