package jminusminus;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import static jminusminus.CLConstants.*;

public class JInitializationBlock extends JMethodDeclaration implements JMember{

    // private boolean invokesInitBlock;

    JClassDeclaration definingClass;

    public JInitializationBlock(int line, ArrayList<String> mods, JBlock body)
    {
        super(line, mods, "InitializationBlock"+ Integer.toString(line), Type.VOID, new ArrayList<JFormalParameter>(), null, body); 
    }

    public void preAnalyze(Context context, CLEmitter partial) {
        super.preAnalyze(context, partial);
        if (isPublic) {
            JAST.compilationUnit.reportSemanticError(line(), "Initialization block can't be public");
        }
        if (isAbstract) {
            JAST.compilationUnit.reportSemanticError(line(), "Initialization block can't be abstract");
        }
        if (isPrivate) {
            JAST.compilationUnit.reportSemanticError(line(), "Initialization block can't be private");
        }
        if (mods.contains("protected")) {
            JAST.compilationUnit.reportSemanticError(line(), "Initialization block can't be protected");
        }

    }

    public JAST analyze(Context context) {
        definingClass = (JClassDeclaration) (context.classContext().definition());
        MethodContext methodContext = new MethodContext(context, isStatic, returnType);
        this.context = methodContext;

        if (!isStatic) {
            // Offset 0 is used to address "this".
            // Allocates a new offset (for example, for a parameter or local variable).
            this.context.nextOffset();
        } 
        // else {
        //     // TODO: if it's static block?
        // }
        if (body != null) {
            body = body.analyze(this.context);
        }
        
        return this;
    }

    public void codegen(CLEmitter output) {
        if (body != null) {
            body.codegen(output);
        }
    }

    public void writeToStdOut(PrettyPrinter p) {
        if(!mods.contains("static")) {
            System.out.println("-----------IIB begins here--------");
            p.printf("<JInitializationBlock line=\"%d\">\n", line());
            p.indentRight();
            if (context != null) {
                context.writeToStdOut(p);
            }
            if (body != null) {
                p.println("<Body>");
                p.indentRight();
                body.writeToStdOut(p);
                p.indentLeft();
                p.println("</Body>");
            }
            p.indentLeft();
            p.println("</JInitializationBlock>");
            System.out.println("-----------IIB ends here--------");
        }
       
        else if(mods.contains("static")) {
            System.out.println("-----------Static Block begins here--------");
            p.printf("<JStaticBlock line=\"%d\">\n", line());
            p.indentRight();
            p.println("</Modifiers>");
            p.indentRight();
            p.println("<Modifier name=\"static\"/>");
            p.indentLeft();
            p.println("</Modifiers>");
            if (context != null) {
                context.writeToStdOut(p);
            }
            if (body != null) {
                p.println("<Body>");
                p.indentRight();
                body.writeToStdOut(p);
                p.indentLeft();
                p.println("</Body>");
            }

            p.indentLeft();
            p.println("</JStaticBlock>");
            System.out.println("-----------Static Block ends here--------");
        }        
    }

}
