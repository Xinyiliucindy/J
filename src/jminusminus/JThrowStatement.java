package jminusminus;

import static jminusminus.CLConstants.*;

class JthrowStatement extends JStatement{
    private JExpression throwExpression;

    public JthrowStatement(int line, JExpression throwExpression){
        super(line);
        this.throwExpression = throwExpression;
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