package junit;

import junit.framework.TestCase;
import pass.Complement;

public class ComplementTest extends TestCase {
	
	Complement complement;


	protected void setUp() throws Exception {
		super.setUp();
	    complement = new Complement();
		}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testBitwise() {

		this.assertEquals(complement.complement(8), -9);
		this.assertEquals(complement.complement(35), -36);
		
	}
}