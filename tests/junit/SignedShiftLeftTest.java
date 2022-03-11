package junit;

import javax.sound.midi.Soundbank;

import junit.framework.TestCase;
// import pass.SignedShiftLeft;
import pass.*;
import java.lang.System;

public class SignedShiftLeftTest extends TestCase{
    private SignedShiftLeft ssleft;

    protected void setUp() throws Exception{
        super.setUp();
        ssleft = new SignedShiftLeft();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testSSL() {
        this.assertEquals(ssleft.shiftleft(8, 2), 32);
        this.assertEquals(ssleft.shiftleft(-42, 1), -84);
        this.assertEquals(ssleft.shiftleft(127, 3), 1016);
        this.assertEquals(ssleft.shiftleft(584, -9), -9);
    }
}
