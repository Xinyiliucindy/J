package jminusminus;

import java.lang.reflect.Member;
import java.util.ArrayList;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.text.StyledEditorKit.BoldAction;

import static jminusminus.CLConstants.*;

public class JInitializationBlock extends JAST implements JMember{

    // private ArrayList<String> mods = new ArrayList<String>();

    /** If the block is static */
    private boolean isStatic=false;

    /** Block */
    private JBlock body;

    /**
     * Constructs an AST node for an initialization block given the line number, 
     * class modifiers, boolean of if the block is static, and the block body.
     * 
     * @param line
     *            line number.
     * @param mods
     *            class modifiers.
     * @param isStatic
     *            if the block is static.
     * @param body
     *            block body.
     */

     public JInitializationBlock(int line, boolean isStatic, JBlock body) {
         super(line);
         this.isStatic = isStatic;
         this.body = body;
     }


    public boolean isStatic() {
        return isStatic;
    }

    public JBlock body() {
        return body;
    }

    public void preAnalyze(Context context, CLEmitter partial) {
        // ? idk how to pre analyze the block body, and what is partial used for 
        
    }

    public JAST analyze(Context context) {
        // TODO: analyze
        return null;
    }

    public void codegen(CLEmitter output) {
        // TODO Auto-generated method stub
        
    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInitializationBlock line=\"%d\">\n", line());
        p.indentRight();
        if(isStatic) {
            p.println("</Modifiers>");
            p.indentRight();
            p.println("<Modifier name=\"static\"/>");
            p.indentLeft();
            p.println("</Modifiers>");
        }
        
        // System.out.println("-----------IIB begins here--------");
        body.writeToStdOut(p);
        // System.out.println("-----------IIB ends here--------");
        p.indentLeft();
        p.println("</JInitializationBlock>");
        

        // feel like sth wrong here, needs double check
    }



}
