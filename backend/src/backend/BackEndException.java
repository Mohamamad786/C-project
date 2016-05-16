package backend;

/**
 * BackEndException:
 * <brief description of class>
 */
public class BackEndException extends Exception {
    public BackEndException(Exception e) {
        super("Error: " + e);
    }
}
