package junit;

import javax.sound.midi.Soundbank;

import junit.framework.TestCase;
// import pass.UnSignedShiftRight;
import pass.*;
import java.lang.System;

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
        this.assertEquals(usright.unsignedshiftright(8, 2), 2);
        this.assertEquals(usright.unsignedshiftright(-42, 1), -21);
        this.assertEquals(usright.unsignedshiftright(127, 3), 15);
        this.assertEquals(usright.unsignedshiftright(-584, 9), -2);
    }
}
