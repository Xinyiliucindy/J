package jminusminus;
import java.util.ArrayList;
import java.util.Random;
import static jminusminus.CLConstants.*;
/**
 * The AST node for a block, which delimits a nested level of scope.
 *
 * @see LocalContext
 */

class JForStatement extends JStatement{
    //init
    private ArrayList<JStatement> forinit;
    /** Test expression. */
    private JExpression condition;

    //update
    private ArrayList<JStatement> update;
    /** The body. */
    private JStatement statement;

    private String fortype;

    //enhanced for loop
    private JFormalParameter init;
    protected JVariableDeclaration forInit;
	protected JExpression forCondition;
	protected JStatement forUpdate;
	protected JStatement body;
    /**
     * The new context (built in analyze()) represented by this block.
     */
    private LocalContext context;


    /**
     * Constructs an AST node for an for-statement given its line number, the 
     * test expression, the consequent, and the alternate.
     * 
     * @param line
     *            line in which the for-statement occurs in the source file.
     * @param forinit
     * @param update
     * @param condition
     * @param statement
     * @param fortype
     * @param init
     *        
     */
    public JForStatement(int line ,JFormalParameter init,JExpression condition,JStatement statement,String fortype)
    {
        super(line);
        this.condition=condition;
        this.init=init;
        this.statement=statement;
        this.fortype=fortype;
        this.body=statement;
    }
    public JForStatement(int line ,ArrayList<JStatement> forinit,JExpression condition,ArrayList<JStatement> update,JStatement statement,String fortype)
    {
        super(line);
        this.condition=condition;
        this.forinit=forinit;
        this.update=update;
        this.statement=statement;
        this.fortype=fortype;
    }

    public ArrayList<JStatement> forinit() {
        return forinit;
    }

    /**
     * Analyzing a block consists of creating a new nested context for that
     * block and analyzing each of its statements within that context.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */


    public JForStatement analyze(Context context){ 
        this.context = new LocalContext(context);
        if(fortype == "basic")
        {
        if(forinit != null)
        {for (JStatement init : forinit)
            init.analyze(context);
        }

        // Analyse condition (must be boolean return type)
	    if (condition != null) {
	        condition = (JExpression) condition.analyze(this.context);
	        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
	    }

        if(update != null)
        {for (JStatement init : update)
            init.analyze(context);}
        
            // Analyse for-loop body
        statement = (JStatement) statement.analyze(this.context);
        }
        else
        {
            //Set variables
		    ArrayList<JVariableDeclarator> forParams = new ArrayList<>();
		
		    JVariable indexVar = new JVariable(line, "v");
		    forParams.add(new JVariableDeclarator(line, indexVar.name(), Type.INT, new JLiteralInt(line, "0")));
		    this.forInit = new JVariableDeclaration(line, null, forParams);
		
		    ArrayList<JVariableDeclarator> blockParams = new ArrayList<>();
		    JVariable arrayVar = new JVariable(line, init.name());
		    blockParams.add(new JVariableDeclarator(line, init.name(), init.type(), new JArrayExpression(line, condition, indexVar)));
		
		    if (body instanceof JBlock == false) {
			    ArrayList<JStatement> jstatements = new ArrayList<>();
			    jstatements.add(body);
			    this.body = new JBlock(line, jstatements);
		    }
		    ((JBlock) this.body).statements.add(0, new JVariableDeclaration(line, null, blockParams));
		 
		
		    //Set condition
		    this.forCondition = new JLessOp(line, indexVar, new JFieldSelection(line, condition, "length"));
		
		    //Set update
		    JExpression incExpr = new JPostIncrementOps(line, indexVar);
		    incExpr.isStatementExpression = true;
		    this.forUpdate = new JStatementExpression(line, incExpr);

            LocalContext lContext = new LocalContext(context);
            init.analyze(lContext);
            condition.analyze(lContext);
    	    if (!condition.type().isArray()) {
    		    JAST.compilationUnit.reportSemanticError(line,
                    "Local variable must be array: \"%s\"", condition.type().toString());
            }
    	
    	    init.type().mustMatchExpected(line, condition.type().componentType());
    	
    	    forInit.analyze(lContext);
    	    forCondition.analyze(lContext);
    	    forCondition.type().mustMatchExpected(line, Type.BOOLEAN);
    	    forUpdate.analyze(lContext);
    	    body.analyze(lContext);
        }
        return this;}
    public void codegen(CLEmitter output){
        if(fortype=="basic")
        {
        String loopLabel = output.createLabel();
	    String endLabel = output.createLabel();
	
	    for (JStatement s : forinit) {
	        s.codegen(output);
	    }

	    output.addLabel(loopLabel);
	    if (condition != null) {
	        condition.codegen(output, endLabel, false);
	    }
	    
	    // Evaluate loop body
	    statement.codegen(output);

	    // If statement has been excecuted correctly
	    // evaluate the forUpdate statement
	    for (JStatement s : update) {
	        s.codegen(output);
	}

	// another iteration.
	output.addBranchInstruction(GOTO, loopLabel);
	output.addLabel(endLabel);
    }
    
    else
    {   
        forInit.codegen(output);
    	// Need two labels
        String test = output.createLabel();
        String out = output.createLabel();

        output.addLabel(test);
        forCondition.codegen(output, out, false);
        
        body.codegen(output);
        forUpdate.codegen(output);
        //element.codegen(output);

        // Unconditional jump back up to test
        output.addBranchInstruction(GOTO, test);

        // The label below and outside the loop
        output.addLabel(out);

        
       
    }
    }
    
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForStatement line=\"%d\">\n", line());
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
        p.indentLeft();
        p.indentRight();
        statement.writeToStdOut(p);
        p.indentLeft();
        p.printf("</JForStatement>\n");
    }
}

