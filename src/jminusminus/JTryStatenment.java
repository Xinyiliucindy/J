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
        
    }
    
}