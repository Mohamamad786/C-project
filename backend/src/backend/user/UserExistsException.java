package backend.user;

/**
 * UserException:
 * <brief description of class>
 */
public class UserExistsException extends Exception {

    private String user;

    public UserExistsException(String user, boolean exist) {
        super(String.format("Username '%s' exists: %b", user, exist));
    }
}
