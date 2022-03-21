package jminusminus;
import java.util.ArrayList;

import static jminusminus.CLConstants.*;


class JForStatement extends JStatement{
    //init
    private ArrayList<JStatement> init;
    /** Test expression. */
    private JExpression condition;

    //update
    private ArrayList<JStatement> update;
    /** The body. */
    private JStatement body;
    /**
     * Constructs an AST node for an for-statement given its line number, the 
     * test expression, the consequent, and the alternate.
     * 
     * @param line
     *            line in which the for-statement occurs in the source file.
     * @param init
     * @param update
     * @param condition
     * @param body
     *        
     */
    public JForStatement(int line ,ArrayList<JStatement> init,JExpression condition,ArrayList<JStatement> update,JStatement body)
    {
        super(line);
        this.condition=condition;
        this.init=init;
        this.update=update;
        this.body=body;
    }
    public JStatement analyze(Context context){ return this;}
    public void codegen(CLEmitter output){}
    public void writeToStdOut(PrettyPrinter p) {}
}

