package log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger:
 * <brief description of class>
 */
public class Log {

    public Log() {

    }

    public void log() {
        try {
            Handler handler = new FileHandler("test.log");
            Logger.getLogger("").addHandler(handler);
        } catch (IOException e) {
            Logger logger = Logger.getLogger("package.name");
            StackTraceElement elements[] = e.getStackTrace();
            for (StackTraceElement element : elements) {
                logger.log(Level.WARNING, element.getMethodName());
            }
        }
    }

    public static void main(String[] args) {
        Log log = new Log();
        log.log();
    }

}
