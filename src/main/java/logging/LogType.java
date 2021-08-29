package logging;

public enum LogType {
    INFO, WARNING, CRITICAL, BLOCKER;

    public static LogType getRandom() {
        double r = Math.random() * 4;
        switch ((int) r) {
            case 1:
                return LogType.WARNING;
            case 2:
                return LogType.CRITICAL;
            case 3:
                return LogType.BLOCKER;
            default:
                return LogType.INFO;
        }
    }
}
