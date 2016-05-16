package backend.user;

/**
 * IllegalUserTypeException:
 * <brief description of class>
 */
public class IllegalUserTypeException extends Exception {

    public IllegalUserTypeException(String type) {
        super(String.format("Invalid User Type: %s", type));
    }
}
