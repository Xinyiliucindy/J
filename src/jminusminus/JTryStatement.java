//step 3 try
//step 4 try
//step 5 try

package jminusminus;

import java.util.ArrayList;
import java.util.Map;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a try-statement.
 *
 * TryStatement: - try Block Catches - try Block [Catches] Finally -
 * TryWithResourcesStatement
 *
 * Catches: - CatchClause {CatchClause}
 *
 * CatchClause: - catch ( CatchFormalParameter ) Block
 *
 * CatchFormalParameter: - {VariableModifier} CatchType VariableDeclaratorId
 *
 * CatchType: - UnannClassType {| ClassType}
 *
 * Finally: - finally Block
 *
 * @see https://docs.oracle.com/javase/specs/jls/se7/html/jls-14.html#jls-14.20
 */
public class JTryStatement extends JStatement {
	/** Try clause. */
	private JBlock tryblock;

	/** Catch clauses. */
	private ArrayList<JCatchClause> catchClauses;

	/** Finally clause. */
	private JBlock finallyblock;

	public JTryStatement(int line, JBlock tryblock, ArrayList<JCatchClause> catchClauses, JBlock finallyblock) {
		super(line);
		this.tryblock = tryblock;
		this.catchClauses = catchClauses;
		this.finallyblock = finallyblock;
	}

	/**
	 * Analyzing the try-statement means analyzing its components.
	 *
	 * @param context context in which names are resolved.
	 * @return the analyzed (and possibly rewritten) AST subtree.
	 */
	public JStatement analyze(Context context) {
		tryblock = tryblock.analyze(context);

		if (catchClauses != null)
			for (JCatchClause _catch : catchClauses)
				_catch.analyze(context);

		if (finallyblock != null)
			finallyblock = finallyblock.analyze(context);

		return this;
	}

	/**
	 * Code generation for a try-statement.
	 *
	 * @param output the code emitter (basically an abstraction for producing the
	 *               .class file).
	 * @see "Introduction to Compiler Constructionin a Java World" pp. 198-203
	 */
	public void codegen(CLEmitter output) {
		String start = output.createLabel(), end = output.createLabel();

		output.addLabel(start);
		tryblock.codegen(output);
		output.addLabel(end);

		if (catchClauses != null)
			for (JCatchClause _catch : catchClauses)
				for (Type t : _catch.getCatchFormalParameter().resolvedTypes()) {
					String handler = output.createLabel();
					output.addLabel(handler);
					output.addExceptionHandler(start, end, handler, t.jvmName());

					_catch.getBlock().codegen(output);

					if (finallyblock != null)
					finallyblock.codegen(output);
				}
		else
			finallyblock.codegen(output);

	}

	/** {@inheritDoc} */
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JTryStatement line=\"%d\">\n", line());

		p.indentRight();
		tryblock.writeToStdOut(p);
		p.indentLeft();

		if (catchClauses != null)
			for (JCatchClause _catch : catchClauses) {
				p.indentRight();
				_catch.writeToStdOut(p);
				p.indentLeft();
			}

		if (finallyblock != null) {
			p.printf("<FinallyBlock>\n");
			p.indentRight();
			finallyblock.writeToStdOut(p);
			p.indentLeft();
			p.printf("</FinallyBlock>\n");
		}
		p.indentLeft();

		p.printf("</JTryStatement>\n");
	}
}