package junit;

import javax.sound.midi.Soundbank;

import junit.framework.TestCase;
import pass.UnSignedShiftRight;

public class UnsignedShiftRightTest extends TestCase{
    private UnSignedShiftRight usright;

    protected void setUp() throws Exception{
        super.setUp();
        usright = new UnSignedShiftRight();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testUSR() {
        this.assertEquals(usright.unsignedshiftright(8, 2), 2);
        this.assertEquals(usright.unsignedshiftright(-42, 1), -21);   //?
        this.assertEquals(usright.unsignedshiftright(127, 3), 15);
    }
}
