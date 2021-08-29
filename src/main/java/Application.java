import logging.Config;
import logging.LogObj;
import logging.LogType;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        LogObj logObj = new LogObj(LogType.INFO, new Config(10, 100, 100));
        for (int i = 0; i < 15; i++) {
            logObj.push(System.currentTimeMillis());
            System.out.printf("Oldest timestamp: %d, current frequency: %d\n" , logObj.peekOldest(), logObj.getFrequency());
            Thread.sleep(1000);
        }
    }
}
