// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * This abstract base class is the AST node for a binary expression. 
 * A binary expression has an operator and two operands: a lhs and a rhs.
 */

abstract class JBinaryExpression extends JExpression {

    /** The binary operator. */
    protected String operator;

    /** The lhs operand. */
    protected JExpression lhs;

    /** The rhs operand. */
    protected JExpression rhs;

    /**
     * Constructs an AST node for a binary expression given its line number, the
     * binary operator, and lhs and rhs operands.
     * 
     * @param line
     *            line in which the binary expression occurs in the source file.
     * @param operator
     *            the binary operator.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */

    protected JBinaryExpression(int line, String operator, JExpression lhs,
            JExpression rhs) {
        super(line);
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    /**
     * {@inheritDoc}
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JBinaryExpression line=\"%d\" type=\"%s\" "
                + "operator=\"%s\">\n", line(), ((type == null) ? "" : type
                .toString()), Util.escapeSpecialXMLChars(operator));
        p.indentRight();
        p.printf("<Lhs>\n");
        p.indentRight();
        lhs.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Lhs>\n");
        p.printf("<Rhs>\n");
        p.indentRight();
        rhs.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Rhs>\n");
        p.indentLeft();
        p.printf("</JBinaryExpression>\n");
    }

}

class JPlusOp extends JBinaryExpression {

    /**
     * Constructs an AST node for an addition expression given its line number,
     * and the lhs and rhs operands.
     * 
     * @param line
     *            line in which the addition expression occurs in the source
     *            file.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */

    public JPlusOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "+", lhs, rhs);
    }

    /**
     * Analysis involves first analyzing the operands. If this is a string
     * concatenation, we rewrite the subtree to make that explicit (and analyze
     * that). Otherwise we check the types of the addition operands and compute
     * the result type.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);
        if (lhs.type() == Type.DOUBLE) {
            lhs.type().mustMatchExpected(line(), Type.DOUBLE);
            rhs.type().mustMatchExpected(line(), Type.DOUBLE);
            type = Type.DOUBLE;

        }
        else if (lhs.type() == Type.INT) {
            lhs.type().mustMatchExpected(line(), Type.INT);
            if (rhs.type() == Type.INT){
                rhs.type().mustMatchExpected(line(), Type.INT);
                type = Type.INT;
            }
            else{
                rhs.type().mustMatchExpected(line(), Type.DOUBLE);
                type = Type.DOUBLE;

            }
        }
        else {
            type = Type.NULLTYPE;
        }
        return this;
    }

    /**
     * Any string concatenation has been rewritten as a 
     * {@link JStringConcatenationOp} (in {@code analyze}), so code generation 
     * here involves simply generating code for loading the operands onto the 
     * stack and then generating the appropriate add instruction.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        if (type == Type.INT)
            output.addNoArgInstruction(IADD);
        else if(type == Type.DOUBLE)
            output.addNoArgInstruction(DADD);
    }

}
//Remainder
class JRemainderOp extends JBinaryExpression {
    public JRemainderOp ( int line , JExpression lhs , JExpression rhs ) {
        super ( line , "/" , lhs , rhs );}
        public JExpression analyze ( Context context ) {
            lhs = ( JExpression ) lhs . analyze ( context );
            rhs = ( JExpression ) rhs . analyze ( context );
            if (lhs.type().equals(Type.DOUBLE)) {
                lhs.type().mustMatchExpected(line(), Type.DOUBLE);
                rhs.type().mustMatchExpected(line(), Type.DOUBLE);
                type = Type.DOUBLE;
            }
            else if (lhs.type().equals(Type.INT)) {
                lhs.type().mustMatchExpected(line(), Type.INT);
                rhs.type().mustMatchExpected(line(), Type.INT);
                type = Type.INT;
            }
            else {
                type = Type.ANY;
                JAST.compilationUnit.reportSemanticError(line(), "wrong types for %");

            }
            return this;
            }
        
        public void codegen ( CLEmitter output ) {
            lhs . codegen ( output );
            rhs . codegen ( output );
            if(type == Type.INT)
            output.addNoArgInstruction(IDIV);
            else if(type == Type.DOUBLE)
            output.addNoArgInstruction(DDIV);
            }
    }
//division
class JDivideOp extends JBinaryExpression {
    public JDivideOp ( int line , JExpression lhs , JExpression rhs ) {
        super ( line , "/" , lhs , rhs );
    }
    public JExpression analyze ( Context context ) {
        lhs = ( JExpression ) lhs . analyze ( context );
        rhs = ( JExpression ) rhs . analyze ( context );
        if (lhs.type().equals(Type.DOUBLE)) {
            lhs.type().mustMatchExpected(line(), Type.DOUBLE);
            rhs.type().mustMatchExpected(line(), Type.DOUBLE);
            type = Type.DOUBLE;
        }
        else if (lhs.type().equals(Type.INT)) {
            lhs.type().mustMatchExpected(line(), Type.INT);
            rhs.type().mustMatchExpected(line(), Type.INT);
            type = Type.INT;
        }
        else {
            type = Type.ANY;
            JAST.compilationUnit.reportSemanticError(line(), "wrong types for /");

        }
        return this;
        }
    public void codegen ( CLEmitter output ) {
        lhs . codegen ( output );
        rhs . codegen ( output );
        if(type == Type.INT)
        output.addNoArgInstruction(IDIV);
        else if(type == Type.DOUBLE)
        output.addNoArgInstruction(DDIV);
        }
 }
//bitwiseor
 class JBitwiseOrOp extends JBinaryExpression {
    public JBitwiseOrOp(int line, JExpression lhs, JExpression rhs) {
        super (line, "|", lhs, rhs);
    }



    public JExpression analyze ( Context context ) {
         lhs = ( JExpression ) lhs . analyze ( context );
            rhs = ( JExpression ) rhs . analyze ( context );
            if (lhs.type().equals(Type.DOUBLE)) {
                lhs.type().mustMatchExpected(line(), Type.DOUBLE);
                rhs.type().mustMatchExpected(line(), Type.DOUBLE);
                type = Type.DOUBLE;
            }
            else if (lhs.type().equals(Type.INT)) {
                lhs.type().mustMatchExpected(line(), Type.INT);
                rhs.type().mustMatchExpected(line(), Type.INT);
                type = Type.INT;
            }
            else {
                type = Type.ANY;
                JAST.compilationUnit.reportSemanticError(line(), "wrong types for %");

            }
            return this;
        }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IOR);
        }    
}

/**
 * The AST node for a subtraction (-) expression.
 */

class JSubtractOp extends JBinaryExpression {

    /**
     * Constructs an AST node for a subtraction expression given its line number,
     * and the lhs and rhs operands.
     * 
     * @param line
     *            line in which the subtraction expression occurs in the source
     *            file.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */

    public JSubtractOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "-", lhs, rhs);
    }

    /**
     * Analyzing the - operation involves analyzing its operands, checking
     * types, and determining the result type.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);
        if (lhs.type() == Type.DOUBLE) {
            lhs.type().mustMatchExpected(line(), Type.DOUBLE);
            rhs.type().mustMatchOneOf(line(), Type.DOUBLE, Type.INT);
            type = Type.DOUBLE;

        }
        else if (lhs.type() == Type.INT) {
            lhs.type().mustMatchExpected(line(), Type.INT);
            if (rhs.type() == Type.INT){
                rhs.type().mustMatchExpected(line(), Type.INT);
                type = Type.INT;
            }
            else{
                rhs.type().mustMatchExpected(line(), Type.DOUBLE);
                type = Type.DOUBLE;

            }
        }
        else {
            type = Type.NULLTYPE;
        }
        return this;
    }


    /**
     * Generating code for the - operation involves generating code for the two
     * operands, and then the subtraction instruction.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        if (type == Type.DOUBLE){
          output.addNoArgInstruction(DSUB);
        }else {
          output.addNoArgInstruction(ISUB);
        }
        
    }

}

/**
 * The AST node for a multiplication (*) expression.
 */

class JMultiplyOp extends JBinaryExpression {

    /**
     * Constructs an AST for a multiplication expression given its line number,
     * and the lhs and rhs operands.
     * 
     * @param line
     *            line in which the multiplication expression occurs in the
     *            source file.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */

    public JMultiplyOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "*", lhs, rhs);
    }

    /**
     * Analyzing the * operation involves analyzing its operands, checking
     * types, and determining the result type.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);
        if (lhs.type() == Type.DOUBLE) {
            lhs.type().mustMatchExpected(line(), Type.DOUBLE);
            rhs.type().mustMatchOneOf(line(), Type.DOUBLE, Type.INT);
            type = Type.DOUBLE;

        }
        else if (lhs.type() == Type.INT) {
            lhs.type().mustMatchExpected(line(), Type.INT);
            if (rhs.type() == Type.INT){
                rhs.type().mustMatchExpected(line(), Type.INT);
                type = Type.INT;
            }
            else{
                rhs.type().mustMatchExpected(line(), Type.DOUBLE);
                type = Type.DOUBLE;

            }
        }
        else {
            type = Type.NULLTYPE;
        }
        return this;
    }

    /**
     * Generating code for the * operation involves generating code for the two
     * operands, and then the multiplication instruction.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        if(type == Type.INT)
            output.addNoArgInstruction(IMUL);
        else if(type == Type.DOUBLE)
            output.addNoArgInstruction(DMUL);
    }

}




/**
 * The AST node for a signed shift left (<<) expression.
 */
class JSSLEFTOp extends JBinaryExpression {

    public JSSLEFTOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "<<", lhs, rhs);
    }

    public JExpression analyze (Context context) { 
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);
        lhs.type().mustMatchExpected(line(), Type.INT);
        rhs.type().mustMatchExpected(line(), Type.INT);
        type = Type.INT;
        return this;
    }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(ISHL);
    }
}


/**
 * The AST node for a signed shift right (>>) expression.
 */
class JSSRIGHTOp extends JBinaryExpression {

    public JSSRIGHTOp(int line, JExpression lhs, JExpression rhs) {
        super(line, ">>", lhs, rhs);
    }

    public JExpression analyze (Context context) { 
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);
        lhs.type().mustMatchExpected(line(), Type.INT);
        rhs.type().mustMatchExpected(line(), Type.INT);
        type = Type.INT;
        return this;
    }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(ISHR);
    }
}


/**
 * The AST node for a unsigned shift right (>>>) expression.
 */
class JUSRIGHTOp extends JBinaryExpression {

    public JUSRIGHTOp(int line, JExpression lhs, JExpression rhs) {
        super(line, ">>>", lhs, rhs);
    }

    public JExpression analyze (Context context) { 
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);
        lhs.type().mustMatchExpected(line(), Type.INT);
        rhs.type().mustMatchExpected(line(), Type.INT);
        type = Type.INT;
        return this;
    }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IUSHR);
    }
}


/**
 * The AST node for a bit exclusive or (^) expression.
 */
class JBitXorOp extends JBinaryExpression {

    public JBitXorOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "^", lhs, rhs);
    }

    public JExpression analyze(Context context) {
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);
        lhs.type().mustMatchExpected(line(), Type.INT);
        rhs.type().mustMatchExpected(line(), Type.INT);
        type = Type.INT;
        return this;
    }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IXOR);
    }

}


/**
 * The AST node for a bitand (&) expression.
 */
class JBitAndOp extends JBinaryExpression {

    public JBitAndOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "&", lhs, rhs);
    }

    public JExpression analyze(Context context) {
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);
        lhs.type().mustMatchExpected(line(), Type.INT);
        rhs.type().mustMatchExpected(line(), Type.INT);
        type = Type.INT;
        return this;
    }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IAND);
    }

}