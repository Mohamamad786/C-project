package unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * unit.BackEndTestSuite:
 * <brief description of class>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataTest.class,
        UserTest.class,
        AccountsTest.class,
        TicketsTests.class,
        RecordTest.class,
        TransactionsTest.class
})
public class BackEndTestSuite {
}
