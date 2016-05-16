package unit;

import backend.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * unit.UserTest:
 * <brief description of class>
 */
public class UserTest {

    private User user1;
    private User user2;

    private String name;
    private String type;
    private double credit;

    @Before
    public void setUp() {
        name = "Nicholas";
        type = "AA";
        credit = 999999999;
        user1 = new User(name, type, credit);
        user2 = new User("User2", type, 0);
    }

    @After
    public void tearDown() {
        user1 = null;
        user2 = null;
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals(String.class, user1.getType().getClass());
        assertEquals(type, user1.getType());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(String.class, user1.getName().getClass());
        assertEquals(name, user1.getName());
    }

    @Test
    public void testGetCredit() throws Exception {
        assertEquals(credit, user1.getCredit());
    }

    @Test
    public void testAddCredit() throws Exception {
        double cur = user2.getCredit();
        assertEquals(0, user2.addCredit(500));
        assertEquals(500.00, user2.getCredit());
    }

    @Test
    public void testRemoveCredit() throws Exception {
        double cur = user1.getCredit();
        assertEquals(999999999.00, cur);
        double rem = 5.55;
        assertEquals(0, user1.removeCredit(rem));
        assertEquals(cur - rem, user1.getCredit());
    }
}
