package backend.user;

/**
 * IllegalUserNameException:
 * <brief description of class>
 */
public class IllegalUserNameException extends Exception {

    public IllegalUserNameException(String name) {
        super(name + " is too long");
    }
}
