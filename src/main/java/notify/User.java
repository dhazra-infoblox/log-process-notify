package notify;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User {
    private final String name;
    private @Generated String id;

    public void sendMail() {
        System.out.println("Mail sent to " + this.name);
    }
}