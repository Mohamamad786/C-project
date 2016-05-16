package backend.user;

/**
 * BuyStandard:
 * <brief description of class>
 */
public class BuyStandard extends User {

    public static final String BS = "BS";

    public BuyStandard(String name, double credit) {
        super(name, BS, credit);
    }
}
