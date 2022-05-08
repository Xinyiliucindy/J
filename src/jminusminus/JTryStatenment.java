//step 2 not complete

package jminusminus;

import static jminusminus.CLConstants.*;

import java.util.ArrayList;

class JTryStatement extends JStatement{

    private JBlock tryBlock;

    private ArrayList<JFormalParameter> catchParameters;

    private ArrayList<JBlock> catchBlocks;

    private JBlock finallyBlock;

    public JTryStatement(int line, JBlock tryBlock, ArrayList<JFormalParameter> catchParameters,
                         ArrayList<JBlock> catchBlocks, JBlock finallyBlock){
        super(line);
        this.tryBlock = tryBlock;
        this.catchParameters = catchParameters;
        this.catchBlocks = catchBlocks;
        this.finallyBlock = finallyBlock;
    }

    @Override
    public JAST analyze(Context context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void codegen(CLEmitter output) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {
        // TODO Auto-generated method stub
        p.printf("<JTryCatchStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TryBlock>\n");
        p.indentRight();
        tryBlock.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TryBlock>\n");


        if(this.catchBlocks != null) {

            for(int i = 0; i < catchBlocks.size(); i++) {
                p.printf("<CatchBlock>\n");
                p.indentRight();

                p.printf("<CatchParameter>\n");
                p.indentRight();
                catchParameters.get(i).writeToStdOut(p);
                p.indentLeft();
                p.printf("</CatchParameter>\n");
                
                catchBlocks.get(i).writeToStdOut(p);
                p.indentLeft();
                p.printf("</CatchBlock>\n");
            }
        }


        if (finallyBlock != null) {
            p.printf("<FinallyBlock>\n");
            p.indentRight();
            finallyBlock.writeToStdOut(p);
            p.indentLeft();
            p.printf("</FinallyBlock>\n");
        }
        p.indentLeft();
        p.printf("</JTryCatchStatement>\n");
    }
    
}