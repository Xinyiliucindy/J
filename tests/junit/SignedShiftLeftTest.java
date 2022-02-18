package junit;

import javax.sound.midi.Soundbank;

import junit.framework.TestCase;
import pass.SignedShiftLeft;

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
        this.assertEquals(ssleft.SignedShiftLeft(8, 2), 32);
        this.assertEquals(ssleft.SignedShiftLeft(-42, 1), -84);
        this.assertEquals(ssleft.SignedShiftLeft(127, 3), 1016);
    }
}
