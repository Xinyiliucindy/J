package junit;

import junit.framework.TestCase;
import pass.For;

public class ForloopTest {
    private For forlooptest;
    protected void setUp() throws Exception {
        super.setUp ();
        forlooptest = new For();
    }

    protected void tearDown () throws Exception {
        super.tearDown ();
    }

    public void testDivide () {
        this.assertEquals(forlooptest.forloop(1), 1);
        this.assertEquals(forlooptest.forloop(10), 10);
        this.assertEquals(forlooptest.forloop(20), 20);
    }
}
    
