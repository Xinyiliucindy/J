package junit;

import junit.framework.TestCase;
import pass.BitwiseAnd;

public class BitwiseAndTest extends TestCase {
    private BitwiseAnd bitAnd;
    
    protected void setUp() throws Exception {
        super.setUp();
        bitAnd = new BitwiseAnd();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBitwiseAnd() {
        this.assertEquals(bitAnd.bitand(2, 3), 2);
        this.assertEquals(bitAnd.bitand(255, 1), 1);
        this.assertEquals(bitAnd.bitand(255, 255), 255);
        this.assertEquals(bitAnd.bitand(127, 128), 0);
    }
}
