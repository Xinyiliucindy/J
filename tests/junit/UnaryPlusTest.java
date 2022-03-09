package junit;

import junit.framework.TestCase;
import pass.Unaryplus;
public class UnaryPlusTest {
    private Unaryplus unaryplus;

    protected void setUp() throws Exception {
        super.setUp();
        unaryplus = new Unaryplus();
    }

    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testUnaryplus(){
        int i =2;
		char ch = 'A';
		this.assertEquals(UPlus.uplus(+i), 2);
		this.assertEquals(UPlus.uplus(+ch), 65);
    }
    
}