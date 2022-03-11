package junit;

import junit.framework.TestCase;
// import pass.BitwiseOr;
import pass.*;
import java.lang.System;

public class BitwiseOrTest extends TestCase {
	
	private BitwiseOr bitwiseor;


	protected void setUp() throws Exception {
		super.setUp();
	    bitwiseor = new BitwiseOr();
		}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testBitwise() {

		this.assertEquals(bitwiseor.bitwiseOR(17, 1), 17);
		this.assertEquals(bitwiseor.bitwiseOr(20, 30), 30);
		this.assertEquals(bitwiseor.bitwiseOr(61, 26), 63);
		this.assertEquals(bitwiseor.bitwiseOr(128, 0), 128);

	}
}