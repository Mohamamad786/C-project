package backend.user;

/**
 * FullStandard:
 * <brief description of class>
 */
public class FullStandard extends User {

    public static final String FS = "FS";

    public FullStandard(String name, double credit) {
        super(name, FS, credit);
    }
}
