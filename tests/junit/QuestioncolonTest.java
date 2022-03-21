package junit;

import junit.framework.TestCase;
import pass.Questioncolon;
public class QuestioncolonTest {
    private Questioncolon questioncolon;
    protected void setUp() throws Exception {
        super.setUp ();
        questioncolon = new Questioncolon();
    }

    protected void tearDown () throws Exception {
        super.tearDown ();
    }

    public void testDivide () {
        this.assertEquals(questioncolon.questioncolon(10), 1);
        this.assertEquals(questioncolon.questioncolon(1), 0);
        this.assertEquals(questioncolon.questioncolon(2), 0);
    }
}
