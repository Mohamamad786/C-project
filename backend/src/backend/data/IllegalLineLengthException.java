package backend.data;

/**
 * LineLengthException:
 * <brief description of class>
 */
public class IllegalLineLengthException extends Exception {
    public IllegalLineLengthException(int lineLength, int LINELENGTH) {
        super(String.format("%d != %d", lineLength, LINELENGTH));
    }
}
