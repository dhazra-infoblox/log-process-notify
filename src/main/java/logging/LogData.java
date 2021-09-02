package logging;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LogData {
    private @NonNull LogType logType;
    private @NonNull Long timeStamp;
    private String someData;
}
