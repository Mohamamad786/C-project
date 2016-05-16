package func;


import backend.BackEnd;
import backend.data.FatalErrorException;
import backend.event.Tickets;
import backend.transaction.Record;
import backend.transaction.Regular;
import backend.transaction.Transactions;
import backend.user.Accounts;
import backend.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;

/**
 * DoCreateTest:
 * <brief description of class>
 */
@RunWith(Parameterized.class)
public class DoCreateTest {

    // Accounts Constants

    // Admin Test backend.data
    private static final String adminLine = "Admin           AA 999999999";
    private static final User adminUser = new User("Admin", "AA", 999999999);

    private static final String adminLogout = "00 Admin           AA 999999999";


    // FullStandard Test Data
    private static final String fullLine = "FullStandard    FS 000005000";
    private static final User fullUser = new User("FullStandard", "FS", 5000);

    private static final String fullLogout = "00 FullStandard   AA 999999999";


    // BuyStanadard Test Data
    private static final String buyLine = "BuyStandard     BS 000000100";
    private static final User buyUser = new User("BuyStandard", "BS", 100);

    private static final String buyLogout = "00 BuyStandard     BS 000000100";


    // SellStandard Test Data
    private static final String sellLine = "SellStandard    SS 000000000";
    private static final User sellUser = new User("SellStandard", "SS", 0);

    private static final String sellLogout = "00 SellStandard    SS 000000000";

    // Transaction constants
    private static final String Create = "01";
    private static final String Delete = "02";
    private static final String Buy = "04";
    private static final String Return = "05";
    private static final String AddCredit = "06";

    private static String makeLogout(String line) {
        return "00 " + line;
    }

    private static String makeCreate(String line) {
        return "01 " + line;
    }


    private BackEnd op;
    private Transactions tr;
    private Accounts ac;
    private Tickets tk;
    private String[] trData;
    private String[] acData;
    private boolean exp;

    public DoCreateTest(String[][] data, boolean exp) {
        this.trData = data[0];
        this.acData = data[1];
        this.exp = exp;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        String[][] test1 = new String[][]{
                {makeCreate(fullLine), makeLogout(adminLine)},
                {adminLine}
        };

        String[][] test2 = new String[][]{
                {makeCreate(sellLine), makeLogout(adminLine)},
                {adminLine}
        };

        String[][] test3 = new String[][]{
                {makeCreate(buyLine), makeLogout(adminLine)},
                {adminLine}
        };

        String[][] test4 = new String[][]{
                {makeCreate(adminLine), makeLogout(adminLine)},
                {adminLine}
        };

        String[][] test5 = new String[][]{
                {makeCreate(sellLine), makeCreate(sellLine), makeLogout(adminLine)},
                {adminLine}
        };

        String[][] test6 = new String[][]{
                {makeCreate(sellLine), makeLogout(fullLine)},
                {adminLine, fullLine}
        };


        return Arrays.asList(new Object[][]{
                {test1, true},
                {test2, true},
                {test3, true},
                {test4, false},
                {test5, false},
                {test6, false}
        });

    }

    @Before
    public void setUp() throws FatalErrorException {
        this.tr = new Transactions();
        this.ac = new Accounts();
        this.tk = new Tickets();
        this.op = new BackEnd(tr, ac, tk);
        for (String line : trData)
            tr.decode(line);
        for (String line : acData)
            ac.decode(line);
    }

    @After
    public void tearDown() {
        tr = null;
        ac = null;
        tk = null;
        op = null;
    }

    @Test
    public void testGetTransaction() throws Exception {
        // get the regular transaction record from tr
        Regular cur = (Regular) tr.getTransaction();
        assertNotNull(cur);
        System.out.printf("code:%d name:%s type:%s credit:%.2f \n",
                cur.getCode(), cur.getName(), cur.getType(), cur.getCredit());
    }

    @Test
    public void testGetLoggedIn() throws Exception {
        String name = tr.getLoggedInUser();
        User user = ac.getUser(name);
        assertNotNull(name);
        assertNotNull(user);
        System.out.printf("name:%s type:%s credit:%.2f \n",
                user.getName(), user.getType(), user.getCredit());
    }


    @Test
    public void testDoCreate() throws Exception {
        // set LoggedIn user
        User cur = op.setLoggedIn();

        // make the private method accessible
        Method doCreate = BackEnd.class.getDeclaredMethod("doCreate", Regular.class);
        doCreate.setAccessible(true);

        Record curRecord = tr.getTransaction();
        Record nextRecord;
        int ret = 0;
        while ((nextRecord = tr.getTransaction()) != null) {
            ret = (Integer) doCreate.invoke(op, curRecord);
            curRecord = nextRecord;
        }
        if (exp)
            assertEquals(0, ret);
        else
            assertNotSame(0, ret);
    }
}
