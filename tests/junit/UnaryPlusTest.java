package junit;

import junit.framework.TestCase;
// import pass.Unaryplus;
import pass.*;
import java.lang.System;

public class UnaryPlusTest extends TestCase{
    private Unaryplus unaryplus;

    protected void setUp() throws Exception {
        super.setUp();
        unaryplus = new Unaryplus();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testUnaryplus(){
        int i = 2;
        int j = -1;
		char ch1 = 'A';
        char ch2 = 'z';
		this.assertEquals(unaryplus.uplus(+i), 2);
        this.assertEquals(unaryplus.uplus(+j), -1);
		this.assertEquals(unaryplus.uplus(+ch1), 65);
        this.assertEquals(unaryplus.uplus(+ch2), 122);
    }
    
}