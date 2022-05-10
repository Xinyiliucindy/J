package jminusminus;
import java.util.ArrayList;

import static jminusminus.CLConstants.*;


class JForStatement extends JStatement{
    //init
    private ArrayList<JStatement> forinit;
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
     * @param forinit
     * @param update
     * @param condition
     * @param body
     *        
     */
    public JForStatement(int line ,ArrayList<JStatement> forinit,JExpression condition,ArrayList<JStatement> update,JStatement body)
    {
        super(line);
        this.condition=condition;
        this.forinit=forinit;
        this.update=update;
        this.body=body;
    }
    public JStatement analyze(Context context){ return this;}
    public void codegen(CLEmitter output){}
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForStbinatement line=\"%d\">\n", line());
        p.indentRight();
        if(forinit != null){
            p.printf("<ForInit>\n");
            for(JStatement i:forinit){
                p.indentRight();
                i.writeToStdOut(p);
                p.indentLeft();
            }
            p.printf("</ForInit>\n");
        }
        p.indentLeft();
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        if(update != null){
            p.printf("<ForUpdate>\n");
            for(JStatement i:update){
                p.indentRight();
                i.writeToStdOut(p);
                p.indentLeft();
            }
            p.printf("</ForUpdate>\n");
        }
    }
}

