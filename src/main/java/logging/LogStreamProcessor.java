package logging;

import lombok.AllArgsConstructor;
import notify.Event;
import notify.LogNotifyEvent;
import notify.Message;
import notify.Topic;

import java.util.List;

@AllArgsConstructor
public class LogStreamProcessor implements Processor {
    // If the low level module is stored in some real cache/database,
    // We can fetch all the properties at once in this class' Constructor.
    // And then we can fetch the required property based on LogType.

    private LogBrowserImpl browser;
    private List<LogData> stream;

    @Override
    public void process() { // high-level module
        for (LogData log : stream) {

            LogCache cache = browser.findLogsOf(log.getLogType());
            cache.push(log);
            Config config = browser.findConfigOf(log.getLogType());

            if(cache.size() > config.getFrequency()) {
                // logs have reached the config frequency
                if(log.getTimeStamp() - config.getDuration() <= cache.peekOldest().getTimeStamp()) {
                    // logs have reached the config frequency within config duration
                    Topic topic = browser.findTopicOf(log.getLogType());

                    if(topic.isNotifiable(config.getWaitTime(), log.getTimeStamp())) {
                        // config wait time has been served
                        new LogNotifyEvent(
                                new Message(
                                        log.getLogType(),
                                        "Frequency Limit exhausted",
                                        log.getTimeStamp())
                                    )
                                .send(topic);

                        cache.flush();
                    }
                }

                cache.removeOldest();
            }
        }
    }
}
