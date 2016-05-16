package unit;

import backend.user.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * unit.AccountsTest:
 * <brief description of class>
 */
public class AccountsTest {

    private Accounts accounts;

    @Before
    public void setUp() {
        accounts = new Accounts();
    }

    @After
    public void tearDown() {
        accounts = null;
    }

    @Test
    public void testNewUser() throws Exception {
        // Valid new user backend.data
        String name = "Nicholas";
        String type = "AA";
        double credit = 999999999;

        assertEquals(0, accounts.newUser(name, type, credit));
        User tmp = accounts.getUser(name);
        assertEquals(name, tmp.getName());
        assertEquals(type, tmp.getType());
        assertEquals(credit, tmp.getCredit());

        // Error: Invalid Name: Name exceeds ma
        name = "NNNNNNNNNNNNNNNNNNNNNNN";
        assertEquals(1, accounts.newUser(name, type, credit));
        tmp = accounts.getUser(name);
        assertEquals(null, tmp);

        // Error: Invalid Name: Name Exists
        name = "Nicholas";
        assertEquals(2, accounts.newUser(name, type, credit));
        tmp = accounts.getUser(name);
        assertNotNull(tmp);

        // Error: Invalid Type
        name = "InvalidType";
        type = "WW";
        assertEquals(3, accounts.newUser(name, type, credit));
        tmp = accounts.getUser(name);
        assertNull(tmp);
        type = "00";
        assertEquals(3, accounts.newUser(name, type, credit));
        tmp = accounts.getUser(name);
        assertNull(tmp);
        type = "aa";
        assertEquals(3, accounts.newUser(name, type, credit));
        tmp = accounts.getUser(name);
        assertNull(tmp);
        type = "bs";
        assertEquals(3, accounts.newUser(name, type, credit));
        tmp = accounts.getUser(name);
        assertNull(tmp);
        type = "AAA";
        assertEquals(3, accounts.newUser(name, type, credit));
        tmp = accounts.getUser(name);
        assertNull(tmp);

        // Error: Invalid Credit
        name = "InvalidCredit";
        type = "AA";
        credit = -200;
        assertEquals(4, accounts.newUser(name, type, credit));
        tmp = accounts.getUser(name);
        assertNull(tmp);
        credit = 1000000000;
        assertEquals(4, accounts.newUser(name, type, credit));
        tmp = accounts.getUser(name);
        assertNull(tmp);
        name = "ValidCredit1";
        credit = 999999999;
        assertEquals(0, accounts.newUser(name, type, credit));
        tmp = accounts.getUser(name);
        assertNotNull(tmp);
        name = "ValidCredit2";
        credit = 0;
        assertEquals(0, accounts.newUser(name, type, credit));
        tmp = accounts.getUser(name);
        assertNotNull(tmp);
    }

    @Test
    public void testDeleteUser() throws UserExistsException {
        String name = "ValidDelete";
        String type = "AA";
        double credit = 400;

        try {
            assertEquals(0, accounts.newUser(name, type, credit));
        } catch (CreditLimitException e) {
            e.printStackTrace();
        } catch (IllegalUserTypeException e) {
            e.printStackTrace();
        } catch (IllegalUserNameException e) {
            e.printStackTrace();
        }
        assertEquals(0, accounts.deleteUser(name));

        // Error: Accounts does not exist
        name = "InvalidDelete";
        assertNull(accounts.getUser(name));
        assertEquals(2, accounts.deleteUser(name));

        name = "InvalidDelete";
        assertNull(accounts.getUser(name));
        assertEquals(2, accounts.deleteUser(name));
    }

    @Test
    public void testGetUser() throws Exception {
        // Valid new user backend.data
        String name = "Nicholas";
        String type = "AA";
        double credit = 999999999;

        assertEquals(0, accounts.newUser(name, type, credit));

        User tmp = accounts.getUser(name);
        assertNotNull(tmp);
        assertEquals(name, tmp.getName());
        assertEquals(type, tmp.getType());
        assertEquals(credit, tmp.getCredit());

        name = "NameDNE";
        tmp = accounts.getUser(name);
        assertNull(tmp);
    }

    @Test
    public void testDecode() throws Exception {
        String name = "Nicholas";
        String type = "AA";
        double credit = 999999999;

        String line = "Nicholas        AA 999999999";
        assertEquals(0, accounts.decode(line));
        User tmp = accounts.getUser(name);
        assertNotNull(tmp);
        assertEquals(name, tmp.getName());
        assertEquals(type, tmp.getType());
        assertEquals(credit, tmp.getCredit());

        line = "Invalid         AA1999999999";
        assertEquals(2, accounts.decode(line));
        tmp = accounts.getUser("Invalid");
        assertNull(tmp);

        assertEquals(0, accounts.deleteUser(name));
        credit = 999999.99;
        line = "Nicholas        AA 999999.99";
        assertEquals(0, accounts.decode(line));
        tmp = accounts.getUser(name);
        assertNotNull(tmp);
        assertEquals(name, tmp.getName());
        assertEquals(type, tmp.getType());

        assertEquals(credit, tmp.getCredit());
    }

    @Test
    public void testEncode() throws Exception {
        String name = "Nicholas";
        String type = "AA";
        double credit = 999999999;
        String line = "Nicholas        AA 999999999";

//        assertEquals(0, accounts.readData());

        assertEquals(0, accounts.decode(line));
        assertEquals(line, accounts.encode());
    }
}
