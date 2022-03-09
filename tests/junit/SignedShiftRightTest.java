package junit;

import javax.sound.midi.Soundbank;

import junit.framework.TestCase;
import pass.SignedShiftRight;

public class SignedShiftRightTest extends TestCase{
    private SignedShiftRight ssright;

    protected void setUp() throws Exception{
        super.setUp();
        ssright = new SignedShiftRight();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testSSR() {
        this.assertEquals(ssright.shiftright(8, 2), 2);
        this.assertEquals(ssright.shiftright(-42, 1), -21);
        this.assertEquals(ssright.shiftright(127, 3), 15);
    }
}

