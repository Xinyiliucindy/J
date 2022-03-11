package junit;

import junit.framework.TestCase;
import pass.*;
import java.lang.System;

public class BitXorTest extends TestCase{
    private Xor Xor;

    protected void setUp() throws Exception{
        super.setUp();
        Xor = new Xor();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testXor(){

        this.assertEquals(Xor.xor(127,128), 255);
        this.assertEquals(Xor.xor(255,255), 0);
        this.assertEquals(Xor.xor(46,24), 54);
        this.assertEquals(Xor.xor(25,1), 24);
    }
}