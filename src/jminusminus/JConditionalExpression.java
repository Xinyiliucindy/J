package jminusminus;
import static jminusminus.CLConstants.*;
public class JConditionalExpression extends JExpression {
    private JExpression rhs;

    private JExpression condition;
    private JExpression assign;

    /**
     * Constructs an AST node for a conditional expression.
     *
     * @param line      line in which the conditional expression occurs in the source file.
     * @param rhs test expression.
     * @param condition
     * @param assign  
     */
    public JConditionalExpression(int line, JExpression rhs, JExpression condition,
                                  JExpression assign) {
        super(line);
        this.rhs = rhs;
        this.condition = condition;
        this.assign = assign;
        
    }
    public JExpression analyze(Context context){ return this;}
    public void codegen(CLEmitter output){}
    public void writeToStdOut(PrettyPrinter p) {}
}
