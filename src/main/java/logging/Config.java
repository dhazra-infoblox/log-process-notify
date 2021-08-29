package logging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Config {
    private int frequency;
    private int duration;
    private int waitTime;
}