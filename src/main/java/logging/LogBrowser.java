package logging;

import notify.Topic;

public interface LogBrowser {
    LogCache findLogsOf(LogType logType);
    Config findConfigOf(LogType logType);
    Topic findTopicOf(LogType logType);
}
