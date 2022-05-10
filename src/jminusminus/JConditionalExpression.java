package jminusminus;
import static jminusminus.CLConstants.*;
public class JConditionalExpression extends JExpression {
    private JExpression condition;

    private JExpression firstpart;
    private JExpression secondpart;

    /**
     * Constructs an AST node for a conditional expression.
     *
     * @param line      line in which the conditional expression occurs in the source file.
     * @param condition test expression.
     * @param firstpart
     * @param secondpart
     */
    public JConditionalExpression(int line, JExpression condition, JExpression firstpart,
                                  JExpression secondpart) {
        super(line);
        this.condition = condition;
        this.firstpart = firstpart;
        this.secondpart = secondpart;
        
    }
    public JExpression analyze(Context context){ 
        condition = (JExpression) condition.analyze(context);
        firstpart = (JExpression) firstpart.analyze(context);
        secondpart = (JExpression) secondpart.analyze(context);
        
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        secondpart.type().mustMatchExpected(line(), firstpart.type());
        type = firstpart.type();    
        return this;}
    public void codegen(CLEmitter output){
        String thenLabel = output.createLabel();
        String elseLabel = output.createLabel();

        condition.codegen(output, thenLabel, false);

        condition.codegen(output);

        if (firstpart != null) {
            output.addBranchInstruction(GOTO, elseLabel);
        }

        output.addLabel(thenLabel);
        
        if (secondpart != null) {
            secondpart.codegen(output);
            output.addLabel(elseLabel);
        }
    }
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JConditionalExpression line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<First>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</First>\n");
        p.printf("<Second>\n");
        p.indentRight();
        firstpart.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Second>\n");
        p.printf("<Third>\n");
        p.indentRight();
        secondpart.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Third>\n");
        p.indentLeft();
        p.printf("</JConditionalExpression>\n");
    }
}
