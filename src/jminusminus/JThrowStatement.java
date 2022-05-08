package jminusminus;

import static jminusminus.CLConstants.*;
//step 3 throw
//step 4 throw
//step 5 throw

/**
 * The AST node for a throwexpression
 */

class JthrowStatement extends JStatement{
    /** Thrown expression. */
    private JExpression throwExpression;

    /** Method for thrown expression */
    private Constructor constructor;

    /** Types of the arguments. */

    private Type[] argTypes;

        /**
     * Constructs an AST node for a throw-statement given its line number and the expression
     * 
     * @param line
     *            line in which the while-statement occurs in the source file.
     * @param expr
     *            the expression.
     */
    public JthrowStatement(int line, JExpression throwExpression){
        super(line);
        this.throwExpression = throwExpression;
    }



    /**
     * Analysis involves...
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
    @Override
    public JAST analyze(Context context) {
        throwExpression = (JExpression) throwExpression.analyze(context);

        //check if the throwExpression is the type throwable
        
        return this;
    }


    /**
     * Generates code for the while loop.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        throwExpression.codegen(output);
        output.addNoArgInstruction(ATHROW);
    }




    @Override
    public void writeToStdOut(PrettyPrinter p) {
        // TODO Auto-generated method stub
        p.printf("<JThrowExpression line=\"%d\">\n", line());
        p.indentRight();
        throwExpression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</JThrowExpression>\n");
    }

}