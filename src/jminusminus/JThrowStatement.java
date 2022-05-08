//step 3 throw
//step 4 throw
//step 5 throw


package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a throw-statement. If the enclosing method is non-void, then
 * there is a value to return, so we keep track of the expression denoting that
 * value and its type.
 */
class JThrowStatement extends JStatement {
	/** The throwed expression. */
	private JExpression throwExpression;

	public JThrowStatement(int line, JExpression throwExpression) {
		super(line);
		this.throwExpression = throwExpression;
	}

	public JStatement analyze(Context context) {
		MethodContext  methodContext = context.methodContext();

		return this;
	}

	/**
	 * Code generation for a throw statement. Constructs, initializes, and throws
	 * the exception object.
	 *
	 * @param output the code emitter (basically an abstraction for producing the
	 *               .class file).
	 */
	public void codegen(CLEmitter output) {

        // throwExpression.codegen(output);
        // if (throwExpression.type() == Type.INT
        //     || throwExpression.type() == Type.BOOLEAN
        //     || throwExpression.type() == Type.CHAR) {
        //     output.addNoArgInstruction(IRETURN);
        // } else {
        //     output.addNoArgInstruction(ARETURN);
        // }
}

	/** {@inheritDoc} */
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JThrowStatement line=\"%d\">\n", line());
		p.indentRight();
		throwExpression.writeToStdOut(p);
		p.indentLeft();
		p.printf("</JThrowStatement>\n");
	}
}