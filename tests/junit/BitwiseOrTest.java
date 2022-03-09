package junit;

import junit.framework.TestCase;
import pass.BitwiseOr;

public class BitwiseOrTest extends TestCase {
	
	BitwiseOr bitwiseor;


	protected void setUp() throws Exception {
		super.setUp();
	    bitwiseor = new BitwiseOr();
		}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testBitwise() {

		this.assertEquals(bitwiseor.bitwiseOr(20, 30), 30);
		this.assertEquals(bitwiseor.bitwiseOr(61, 26), 63);
		
	}
}