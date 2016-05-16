package unit;

import backend.transaction.Record;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

/**
 * unit.UserTest:
 * <brief description of class>
 */
@RunWith(Parameterized.class)
public class RecordTest {

    private int code;
    private Record record;

    public RecordTest(int code) {
        this.code = code;
        this.record = new Record(code);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
        });
    }

    @Test
    public void testRecordConstruct() throws Exception {
        assertEquals(code, record.getCode());
    }
}
