package junit;

import javax.sound.midi.Soundbank;

import junit.framework.TestCase;
import pass.UnSignedShiftRight;

public class UnsignedShiftRightTest extends TestCase{
    private UnsignedShiftRight usright;

    protected void setUp() throws Exception{
        super.setUp();
        usright = new UnsignedShiftRight();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testUSR() {
        this.assertEquals(usright.UnsignedShiftRight(8, 2), 2);
        this.assertEquals(usright.UnsignedShiftRight(-42, 1), -21);   //?
        this.assertEquals(usright.UnsignedShiftRight(127, 3), 15);
    }
}
