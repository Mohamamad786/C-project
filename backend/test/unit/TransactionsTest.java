package unit;

import backend.transaction.Regular;
import backend.transaction.Return;
import backend.transaction.SellBuy;
import backend.transaction.Transactions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.*;

/**
 * TranasactionsTest:
 * <brief description of class>
 */
public class TransactionsTest {

    private String line00Logout;
    private String line01Create;
    private String line02Delete;
    private String line03Sell;
    private String line04Buy;
    private String line05Refund;
    private String line06AddCredit;

    private Transactions transactions;

    @Before
    public void setUp() {
        transactions = new Transactions();
        line00Logout = "00 User1           AA 999999999";
        line01Create = "01 User7           AA 999999999";
        line02Delete = "02 User2           AA 999999999";
        line03Sell = "03 test                 User1           050 050.25";
        line04Buy = "04 test                 User1           025 050.25";
        line05Refund = "05 User1           User2           500.25";
        line06AddCredit = "06 User1           AA 000050.20";
    }

    @After
    public void tearDown() {
        transactions = null;
        line00Logout = null;
        line01Create = null;
        line02Delete = null;
        line03Sell = null;
        line04Buy = null;
        line05Refund = null;
        line06AddCredit = null;
    }

    @Test
    public void testGetTransaction() throws Exception {
        // insert transactions in sequential order
        assertEquals(0, transactions.decode(line01Create));
        assertEquals(0, transactions.decode(line02Delete));
        assertEquals(0, transactions.decode(line03Sell));
        assertEquals(0, transactions.decode(line04Buy));
        assertEquals(0, transactions.decode(line05Refund));
        assertEquals(0, transactions.decode(line06AddCredit));
        assertEquals(0, transactions.decode(line00Logout));

        // check if logout was recorded
        assertEquals("User1", transactions.getLoggedInUser());

        // get transactions in the same order as inserted
        assertSame(Regular.class, (transactions.getTransaction()).getClass());
        assertSame(Regular.class, (transactions.getTransaction()).getClass());
        assertSame(SellBuy.class, (transactions.getTransaction()).getClass());
        assertSame(SellBuy.class, (transactions.getTransaction()).getClass());
        assertSame(Return.class, (transactions.getTransaction()).getClass());
        assertSame(Regular.class, (transactions.getTransaction()).getClass());
        assertSame(Regular.class, (transactions.getTransaction()).getClass());
        assertNull(transactions.getTransaction());
    }


    @Test
    public void testGetLoggedInUser() throws Exception {
        // create a set of  logged in users
        String line0 = "00 User0           AA 999999999";
        String line1 = "00 User1           AA 999999999";
        String line2 = "00 User2           AA 999999999";

        // insert each user after some transactions
        assertEquals(0, transactions.decode(line04Buy));
        assertEquals(0, transactions.decode(line0));
        assertEquals(0, transactions.decode(line04Buy));
        assertEquals(0, transactions.decode(line1));
        assertEquals(0, transactions.decode(line04Buy));
        assertEquals(0, transactions.decode(line2));

        assertEquals("User0", transactions.getLoggedInUser());
        assertEquals("User1", transactions.getLoggedInUser());
        assertEquals("User2", transactions.getLoggedInUser());
    }

    @Test
    public void testDecode() throws Exception {
        assertNull(transactions.getTransaction());
        assertEquals(0, transactions.decode(line00Logout));
        assertNotNull(transactions.getTransaction());
        assertEquals("User1", transactions.getLoggedInUser());

        assertNull(transactions.getTransaction());
        assertEquals(0, transactions.decode(line01Create));
        assertNotNull(transactions.getTransaction());


        assertNull(transactions.getTransaction());
        assertEquals(0, transactions.decode(line02Delete));
        assertNotNull(transactions.getTransaction());


        assertNull(transactions.getTransaction());
        assertEquals(0, transactions.decode(line03Sell));
        assertNotNull(transactions.getTransaction());


        assertNull(transactions.getTransaction());
        assertEquals(0, transactions.decode(line04Buy));
        assertNotNull(transactions.getTransaction());


        assertNull(transactions.getTransaction());
        assertEquals(0, transactions.decode(line05Refund));
        assertNotNull(transactions.getTransaction());


        assertNull(transactions.getTransaction());
        assertEquals(0, transactions.decode(line06AddCredit));
        assertNotNull(transactions.getTransaction());
    }
}
