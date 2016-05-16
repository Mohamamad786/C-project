package func;

import backend.BackEnd;
import backend.event.Tickets;
import backend.transaction.Transactions;
import backend.user.Accounts;
import backend.user.User;
import org.junit.Before;
import org.junit.Test;

/**
 * DoBuyTest:
 * <brief description of class>
 */
public class DoAddCreditTest {

    private BackEnd op;
    private Transactions tr;
    private Accounts ac;
    private Tickets tk;

    @Before
    public void setUp() throws Exception {
        tr = new Transactions();
        ac = new Accounts();
        tk = new Tickets();
        this.op = new BackEnd(tr, ac, tk);
    }

    @Test
    public void testAddCredit() throws Exception {
        User user = new User("Nicholas", "AA", 999999999);
        try {
            user.addCredit(1001.00);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

}
