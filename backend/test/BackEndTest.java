import backend.BackEnd;
import backend.event.Tickets;
import backend.transaction.Regular;
import backend.transaction.Transactions;
import backend.user.Accounts;
import backend.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static junit.framework.TestCase.assertEquals;

/**
 * OperationManagerTest:
 * This test class is responsible for running the input output tests on backend.data sets
 */
public class BackEndTest {
    // Transaction constants
    private final String tr1Create = "01 user5           AA 999999999";
    private final String tr2Delete = "02 user5           AA 999999999";
    private final String tr3Sell = "03 event4               User1           050 050.25";
    private final String tr4Buy = "04 event3               User4           000 000005";
    private final String tr5Return = "05 user1           User4           00500";
    private final String tr6AddCredit = "06 user1           AA 000050.20";
    private final String tr0Logout = "00 user1           AA 999999999";

    // Accounts Constants
    private final String user1 = "user1           AA 999999999";
    private final String user2 = "user2           FS 000005000";
    private final String user3 = "user3           BS 000000100";
    private final String user4 = "user4           SS 000000000";

    // Tickets Constants
    private final String ticket1 = "event1               user1           100 000025";
    private final String ticket2 = "event2               user2           050 000.25";
    private final String ticket3 = "event3               user4           004 000005";


    private BackEnd opManager;
    private Transactions transactions;
    private Accounts accounts;
    private Tickets tickets;

    @Before
    public void setUp() {
        this.transactions = new Transactions();
        this.accounts = new Accounts();
        this.tickets = new Tickets();
        opManager = new BackEnd(transactions, accounts, tickets);
    }

    @After
    public void tearDown() {
        transactions = null;
        accounts = null;
        tickets = null;
        opManager = null;
    }

    @Test
    public void testProcessTransactions() throws Exception {
    }

    @Test
    public void testSetLoggedIn() throws Exception {
        // create a transaction of a create and a logout
        transactions.decode(tr1Create);
        transactions.decode(tr0Logout);
        // create a user account
        assertEquals(0, accounts.decode(user1));

// Experienced trouble getting the currently logged in user.
// Discovered test strategies to access private attributes and methods using
// java.lang.reflect library. Problem solved.
///////////////////////////////////////////////////////////////////////////////////
//        Field records = OperationManager.class.getDeclaredField("transactions");
//        records.setAccessible(true);
//
//        Transactions cur = (Transactions) records.get(opManager);
//        System.out.println(cur.getLoggedInUser());
//        cur = (Transactions) records.get(opManager);
//        System.out.println(cur.getLoggedInUser());
//        Field accounts = OperationManager.class.getDeclaredField("accounts");
//        accounts.setAccessible(true);
//
//        Accounts users = (Accounts) accounts.get(opManager);
//        User curUser = users.getUser("user1");
//        TestCase.assertNotNull(curUser);
//        System.out.println(curUser.getName());
///////////////////////////////////////////////////////////////////////////////////

        User user = opManager.setLoggedIn();
        System.out.println(user.getName());
        assertEquals("user1", user.getName());
    }

    @Test
    public void testDoCreate() throws Exception {
        // create a transaction of a create and a logout
        transactions.decode(tr1Create);
        transactions.decode(tr0Logout);
        // create a user account
        accounts.decode(user1);

        opManager.setLoggedIn();

        // accessing private member function of OperationManager class
        Method doCreate = BackEnd.class.getDeclaredMethod("doCreate", Regular.class);
        doCreate.setAccessible(true);
        // create a re
        Regular transaction = (Regular) transactions.getTransaction();
        System.out.printf("code:%d name:%s type:%s credit:%.2f \n", transaction.getCode(),
                transaction.getName(), transaction.getType(), transaction.getCredit());
        int ret = (Integer) doCreate.invoke(opManager, transaction);
        System.out.print(ret);
        assertEquals(0, ret);
    }

    @Test
    public void testMain() throws Exception {

    }
}
