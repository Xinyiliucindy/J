package junit;

import junit.framework.TestCase;
import pass.LogicalOr;

public class LogicalOrTest extends TestCase {
    private LogicalOr logicOr;

    protected void setUp() throws Exception {
        super.setUp();
        logicOr = new LogicalOr();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDivide() {
        this.assertEquals(logicOr.logicalOr(true, true), true);
        this.assertEquals(logicOr.logicalOr(false, true), true);
        this.assertEquals(logicOr.logicalOr(true, false), true);
        this.assertEquals(logicOr.logicalOr(false, false), false);
    }    
}