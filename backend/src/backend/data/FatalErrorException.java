package backend.data;

/**
 * FatalError:
 * <brief description of class>
 */
public class FatalErrorException extends Exception {

    private String file;

    public FatalErrorException(Exception e) {
        super(e.getMessage());
    }

}
