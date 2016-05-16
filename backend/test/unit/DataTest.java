package unit;

import backend.data.Data;
import backend.data.FatalErrorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * unit.unit.AccountsTest:
 * <brief description of class>
 */
public class DataTest {
    protected Data data;

    @Before
    public void setUp() {
        data = new Data("UserAccounts.txt");
    }

    @After
    public void tearDown() {
        data = null;
    }

    @Test
    public void testReadData() throws FatalErrorException {
        data.readData();
    }
}
