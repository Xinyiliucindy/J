package junit;

import junit.framework.TestCase;
// import pass.BitwiseAnd;
import pass.*;
import java.lang.System;

public class BitwiseAndTest extends TestCase {
    
    private BitwiseAnd bitAnd;
    
    protected void setUp() throws Exception {
        super.setUp();
        bitAnd = new BitwiseAnd();
    }

    public void testBitwiseAnd() {
        this.assertEquals(bitAnd.bitand(2, 3), 2);
        this.assertEquals(bitAnd.bitand(256, 1), 0);
        this.assertEquals(bitAnd.bitand(255, 255), 255);
        this.assertEquals(bitAnd.bitand(15, 1), 1);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
